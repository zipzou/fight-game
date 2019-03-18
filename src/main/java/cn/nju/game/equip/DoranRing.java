/**
 * 
 */
package cn.nju.game.equip;

/**
 * 多兰戒
 * @author frank
 *
 */
@Deprecated
public class DoranRing extends Equipment {
	private static final long serialVersionUID = 3968576532927259751L;
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "多兰之戒";
	
	/**
	 * 
	 */
	private DoranRing() {
		super();
	}

	/**
	 * 多兰戒构建器
	 * @author frank
	 *
	 */
	private static class DoranRingBuilder extends AbstractEquipmentBuilder {
		private DoranRingBuilder() {
			equipment = new DoranRing();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 多兰戒构建器
	 * @return 装备构建器
	 */
	public static EquipmentBuilder builder() {
		return new DoranRingBuilder();
	}
	
}
