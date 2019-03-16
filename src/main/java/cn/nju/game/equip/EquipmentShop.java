/**
 * 
 */
package cn.nju.game.equip;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.nju.game.equip.util.EquipmentFileUtil;

/**
 * 装备商店，在这里缓存了所有的装备
 * @author frank
 *
 */
public class EquipmentShop implements EquipmentPool {

	private Logger LOG = Logger.getLogger(EquipmentShop.class);
	
	private Map<String, Equipment> equipments;

	private Set<Equipment> equipmentsSet;
	
	private static class EquipmentShopHolder {
		private static final EquipmentShop  _INSTANCE = new EquipmentShop();
	}
	
	/**
	 * 获取共享装备池
	 * @return 共享装备池
	 */
	public static EquipmentPool sharedPool() {
		return EquipmentShopHolder._INSTANCE;
	}
	
	protected EquipmentShop() {
		equipments = new HashMap<String, Equipment>();
	}
	
	/**
	 * 从配置文件中加载装备
	 */
	private void loadEquipmentsFromConfig() {
		if (LOG.isInfoEnabled()) {
			LOG.info("Start loading equipment config file...");
		}
		try {
			equipmentsSet = EquipmentFileUtil.readEquipments();
			for (Equipment equipment : equipmentsSet) {
				equipments.put(equipment.getName(), equipment);
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("Loading equipment config completed!...");
			}
		} catch (IOException e) {
			e.printStackTrace();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Unable load equipments, there is a IO-Wrong occured!");
			}
		}
	}
	
	/**
	 * 查找装备
	 * @param name 装备名称
	 * @return 装备
	 */
	private Equipment findEquipment(String name) {
		if (equipments.containsKey(name)) {
			return equipments.get(name);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentPool#getEquipment(java.lang.String)
	 */
	public Equipment getEquipment(String name) {
		if (null == equipments) { // 未找到装备，则加载所有装备
			loadEquipmentsFromConfig();
		}
		
		Equipment result = findEquipment(name);
		
		if (null != result) {
			return result;
		}
		loadEquipmentsFromConfig();
		
		return findEquipment(name);
	}

	public Iterable<Equipment> getAllEquipments() {
		if (null == equipmentsSet) {
			loadEquipmentsFromConfig();
		}
		return equipmentsSet;
	}

}
