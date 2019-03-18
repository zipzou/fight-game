/**
 * 
 */
package cn.nju.game.equip;

/**
 * 幽梦之灵
 * @author frank
 *
 */
@Deprecated
public class YoumuuGhostblade extends ComposedEquipment {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2947539401399250797L;
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "幽梦之灵";
	
	private YoumuuGhostblade() {
		super();
	}

	/**
	 * 幽梦之灵构建器
	 * @author frank
	 *
	 */
	private static class YoumuuGhostbladeBuilder extends AbstractComposedEquipmentBuilder {
		private YoumuuGhostbladeBuilder() {
			equipment = new YoumuuGhostblade();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 幽梦之灵构建器
	 * @return 装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new YoumuuGhostbladeBuilder();
	}
}
