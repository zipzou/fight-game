/**
 * 
 */
package cn.nju.game.equip;

/**
 * 增幅典籍
 * @author frank
 *
 */
@Deprecated
public class AmplifyingTome extends ComposedEquipment {
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "增幅典籍";
	
	private AmplifyingTome() {
		super();
	}

	/**
	 * 增幅典籍构建器
	 * @author frank
	 *
	 */
	private static class AmplifyingTomeBuilder extends AbstractComposedEquipmentBuilder {
		private AmplifyingTomeBuilder() {
			equipment = new AmplifyingTome();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 增幅典籍构建器
	 * @return 装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new AmplifyingTomeBuilder();
	}
}
