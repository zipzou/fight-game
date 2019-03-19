/**
 * 
 */
package cn.nju.game.equip.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.equip.Bag;
import cn.nju.game.equip.ComponentIterator;
import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.EquipmentBag;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.equip.util.EquipmentFileUtil;
import cn.nju.game.util.ObjectiveSerializeUtil;

/**
 * @author frank
 *
 */
public class EquipmentTest {
	
	@Test
	public void testParseEquipments() {
		try {
			Set<Equipment> equipments = EquipmentFileUtil.readEquipments();
			assertTrue(27 == equipments.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWriteToFile() {
		Equipment equipment = EquipmentShop.sharedPool().getEquipment("黑色切割者");
		try {
			String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.EQUIPMENT_FILE).toString();
			ObjectiveSerializeUtil.serialize(folder, "zzp", equipment);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWriteBagToFile() throws IOException {
		Bag equipmentBag = new EquipmentBag();
		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("黑色切割者"));
		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("卢登的回声"));
		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("多兰之刃"));
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.EQUIPMENT_FILE).toString();
		ObjectiveSerializeUtil.serialize(folder, "bag-equip-zzp", equipmentBag);
	}
	
	@Test
	public void testReadBagToFile() throws IOException, ClassNotFoundException {
//		Bag equipmentBag = new EquipmentBag();
//		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("黑色切割者"));
//		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("卢登的回声"));
//		equipmentBag.add(EquipmentShop.sharedPool().getEquipment("多兰之刃"));
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.EQUIPMENT_FILE).toString();
		Bag equipmentBag = ObjectiveSerializeUtil.unserialize(folder, "bag-equip-zzp");
		ComponentIterator<Equipment> iterator = equipmentBag.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			count++;
			iterator.next();
		}
		assertTrue(3 == count);
	}
	
	@Test
	public void testReadFromFile() throws ClassNotFoundException, IOException {
		Equipment equip1 = EquipmentShop.sharedPool().getEquipment("黑色切割者");
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.EQUIPMENT_FILE).toString();
		Equipment equip2 = ObjectiveSerializeUtil.unserialize(folder, "zzp");
		assertTrue(equip1.computeArmor() == equip2.computeArmor());
		assertTrue(equip1.computePrice() == equip2.computePrice());
		assertTrue(equip1.computeMagicalResistance() == equip2.getMagicalResistance());
		assertTrue(equip1.computePhysicalDamage() == equip2.computePhysicalDamage());
		assertTrue(equip1.computeMagicDamage() == equip2.computeMagicDamage());
		assertTrue(equip1.getName().equalsIgnoreCase(equip2.getName()));
	}

}
