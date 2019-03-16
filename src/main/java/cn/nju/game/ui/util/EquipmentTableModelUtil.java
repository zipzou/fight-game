/**
 * 
 */
package cn.nju.game.ui.util;

import java.io.InputStream;

import cn.nju.game.equip.Equipment;

/**
 * @author frank
 *
 */
public class EquipmentTableModelUtil extends ModelToTableModelUtil {

	public EquipmentTableModelUtil(String forModel) {
		super(forModel);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getAttr2HeaderFile()
	 */
	@Override
	protected InputStream getAttr2HeaderFile() {
		return Equipment.class.getResourceAsStream("equipment-header.json");
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getHeader2IndexFile()
	 */
	@Override
	protected InputStream getHeader2IndexFile() {
		return Equipment.class.getResourceAsStream("equipment-index.json");
	}

}
