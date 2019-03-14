/**
 * 
 */
package cn.nju.game.equip;

/**
 * 恶魔法典
 * @author frank
 *
 */
@Deprecated
public class FiendishCodex extends ComposedEquipment {
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "恶魔法典";
	
	private FiendishCodex() {
		super();
	}

	/**
	 * 恶魔法典构建器
	 * @author frank
	 *
	 */
	private static class FiendishCodexBuilder extends AbstractComposedEquipmentBuilder {
		private FiendishCodexBuilder() {
			equipment = new FiendishCodex();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 恶魔法典构建器
	 * @return 装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new FiendishCodexBuilder();
	}
}
