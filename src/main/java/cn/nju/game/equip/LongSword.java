/**
 * 
 */
package cn.nju.game.equip;

/**
 * 长剑
 * @author frank
 *
 */
@Deprecated
public class LongSword extends Equipment {
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "长剑";
	
	/**
	 * 
	 */
	private LongSword() {
		super();
	}

	/**
	 * 长剑构建器
	 * @author frank
	 *
	 */
	private static class LongSwordBuilder extends AbstractEquipmentBuilder {
		private LongSwordBuilder() {
			equipment = new LongSword();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 长剑构建器
	 * @return 装备构建器
	 */
	public static EquipmentBuilder builder() {
		return new LongSwordBuilder();
	}
}
