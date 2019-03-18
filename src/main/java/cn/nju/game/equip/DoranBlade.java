/**
 * 
 */
package cn.nju.game.equip;

/**
 * 多兰之刃
 * @author frank
 *
 */
@Deprecated
public class DoranBlade extends Equipment {
	private static final long serialVersionUID = 8193913162967425782L;
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "多兰之刃";
	
	/**
	 * 
	 */
	private DoranBlade() {
		super();
	}

	/**
	 * 多兰刃构建器
	 * @author frank
	 *
	 */
	private static class DoranBladeBuilder extends AbstractEquipmentBuilder {
		private DoranBladeBuilder() {
			equipment = new DoranBlade();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 多兰刃构建器
	 * @return 装备构建器
	 */
	public static EquipmentBuilder builder() {
		return new DoranBladeBuilder();
	}
}
