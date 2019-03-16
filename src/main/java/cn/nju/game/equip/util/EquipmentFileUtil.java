/**
 * 
 */
package cn.nju.game.equip.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.nju.game.equip.ComposedEquipment;
import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.EquipmentBuilder;
import cn.nju.game.equip.SingleEquipment;

/**
 * 装备文件接口
 * 
 * @author frank
 *
 */
public class EquipmentFileUtil {

	private static final String UTF_8_CHARSET = "utf-8";
	private static final String EQUIPMENTS_JSON_FILE = "equipments.json";
	private static final String KEY_SUB_EQUIPMENTS = "subEquipments";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_STRENGTH_HEALTH = "strengthHealth";
	private static final String KEY_RECOVER_HEALTH = "recoverHealth";
	private static final String KEY_RECOVER_ENERGY = "recoverEnergy";
	private static final String KEY_PRICE = "price";
	private static final String KEY_PHYSICAL_DAMAGE = "physicalDamage";
	private static final String KEY_MAGIC_DAMAGE = "magicalDamage";
	private static final String KEY_MAGICAL_RESISTANCE = "magicalResistance";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_ARMOR = "armor";
	private static final String KEY_COMPOSED_EQUIMENTS = "composedEquiments";
	private static final String KEY_SINGLE_EQUIPMENTS = "singleEquipments";

	public static Set<Equipment> readEquipments() throws IOException {
		HashSet<Equipment> equipments = new HashSet<Equipment>();
		InputStream equipmentJsonInput = Equipment.class.getResourceAsStream(EQUIPMENTS_JSON_FILE);
		String equipmentJson = IOUtils.toString(equipmentJsonInput, UTF_8_CHARSET);
		JSONObject equipmentJsonObject = JSON.parseObject(equipmentJson);
		
		Map<Integer, Equipment> equipIdMap = new HashMap<Integer, Equipment>();
		JSONArray singleEquipments = (JSONArray) equipmentJsonObject.get(KEY_SINGLE_EQUIPMENTS);
		
		// 解析单一装备
		for (Object item : singleEquipments) {
			JSONObject singleEquip = (JSONObject) item;
			
			Equipment singleEquipmentObject = SingleEquipment.builder()
					.armor(singleEquip.containsKey(KEY_ARMOR) ? singleEquip.getIntValue(KEY_ARMOR) : 0)
					.description(singleEquip.containsKey(KEY_DESCRIPTION) ? singleEquip.getString(KEY_DESCRIPTION) : null)
					.magicalResistance(singleEquip.containsKey(KEY_MAGICAL_RESISTANCE) ? singleEquip.getIntValue(KEY_MAGICAL_RESISTANCE) : 0)
					.magicalDamage(singleEquip.containsKey(KEY_MAGIC_DAMAGE) ? singleEquip.getInteger(KEY_MAGIC_DAMAGE) : 0)
					.physicalDamage(singleEquip.containsKey(KEY_PHYSICAL_DAMAGE) ? singleEquip.getIntValue(KEY_PHYSICAL_DAMAGE) : 0)
					.price(singleEquip.containsKey(KEY_PRICE) ? singleEquip.getIntValue(KEY_PRICE) : 0)
					.recoverEnergy(singleEquip.containsKey(KEY_RECOVER_ENERGY) ? singleEquip.getFloatValue(KEY_RECOVER_ENERGY) : 0)
					.recoverHealth(singleEquip.containsKey(KEY_RECOVER_HEALTH) ? singleEquip.getFloatValue(KEY_RECOVER_HEALTH) : 0)
					.strengthHealth(singleEquip.containsKey(KEY_STRENGTH_HEALTH) ? singleEquip.getIntValue(KEY_STRENGTH_HEALTH) : 0)
					.name(singleEquip.containsKey(KEY_NAME) ? singleEquip.getString(KEY_NAME) : null)
					.build();
			
			equipIdMap.put(singleEquip.getInteger(KEY_ID), singleEquipmentObject);
		}
		
		// 解析组合装备
		JSONArray composedEquipments = (JSONArray) equipmentJsonObject.get(KEY_COMPOSED_EQUIMENTS);
		for (Object item : composedEquipments) {
			JSONObject composedEquipJsonObject = (JSONObject) item;
			
			// 获取子装备
			JSONArray subEquipmentIds = composedEquipJsonObject.getJSONArray(KEY_SUB_EQUIPMENTS);
			
			EquipmentBuilder composedEquipmentObjectBuilder = ComposedEquipment.builder()
					.armor(composedEquipJsonObject.containsKey(KEY_ARMOR) ? composedEquipJsonObject.getIntValue(KEY_ARMOR) : 0)
					.description(composedEquipJsonObject.containsKey(KEY_DESCRIPTION) ? composedEquipJsonObject.getString(KEY_DESCRIPTION) : null)
					.magicalResistance(composedEquipJsonObject.containsKey(KEY_MAGICAL_RESISTANCE) ? composedEquipJsonObject.getIntValue(KEY_MAGICAL_RESISTANCE) : 0)
					.magicalDamage(composedEquipJsonObject.containsKey(KEY_MAGIC_DAMAGE) ? composedEquipJsonObject.getInteger(KEY_MAGIC_DAMAGE) : 0)
					.physicalDamage(composedEquipJsonObject.containsKey(KEY_PHYSICAL_DAMAGE) ? composedEquipJsonObject.getIntValue(KEY_PHYSICAL_DAMAGE) : 0)
					.price(composedEquipJsonObject.containsKey(KEY_PRICE) ? composedEquipJsonObject.getIntValue(KEY_PRICE) : 0)
					.recoverEnergy(composedEquipJsonObject.containsKey(KEY_RECOVER_ENERGY) ? composedEquipJsonObject.getFloatValue(KEY_RECOVER_ENERGY) : 0)
					.recoverHealth(composedEquipJsonObject.containsKey(KEY_RECOVER_HEALTH) ? composedEquipJsonObject.getFloatValue(KEY_RECOVER_HEALTH) : 0)
					.strengthHealth(composedEquipJsonObject.containsKey(KEY_STRENGTH_HEALTH) ? composedEquipJsonObject.getIntValue(KEY_STRENGTH_HEALTH) : 0)
					.name(composedEquipJsonObject.containsKey(KEY_NAME) ? composedEquipJsonObject.getString(KEY_NAME) : null);

			// 添加子装备
			for (Object subItem : subEquipmentIds) {
				Integer subId = (Integer) subItem;
				composedEquipmentObjectBuilder.add(equipIdMap.get(subId));
			}
			
			equipIdMap.put(composedEquipJsonObject.getInteger(KEY_ID), composedEquipmentObjectBuilder.build());
		}
		
		// 添加到缓存
		Set<Entry<Integer, Equipment>> entry = equipIdMap.entrySet();
		for (Entry<Integer, Equipment> equipMapEntry : entry) {
			equipments.add(equipMapEntry.getValue());
		}
		
		return equipments;
	}

}
