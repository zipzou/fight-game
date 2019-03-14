/**
 * 
 */
package cn.nju.game.equip.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import cn.nju.game.equip.AbstractEquipmentBuilder;
import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.util.EquipmentFileUtil;

/**
 * @author frank
 *
 */
public class EquipmentTest {
	
	@Test
	public void testReadEquipments() {
		InputStream equipInputStream = AbstractEquipmentBuilder.class.getResourceAsStream("equipments.json");
		try {
			System.out.println(IOUtils.toString(equipInputStream, "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseEquipments() {
		try {
			Set<Equipment> equipments = EquipmentFileUtil.readEquipments();
			assertTrue(27 == equipments.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
