/**
 * 
 */
package cn.nju.game.equip;

/**
 * 考尔菲德的战锤
 * @author frank
 *
 */
@Deprecated
public class CaulfieldWarhammer extends ComposedEquipment {
	private static final long serialVersionUID = -8163683956386221744L;
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "考尔菲德的战锤";
	
	private CaulfieldWarhammer() {
		super();
	}

	/**
	 * 考尔菲德的战锤构建器
	 * @author frank
	 *
	 */
	private static class CaulfieldWarhammerBuilder extends AbstractComposedEquipmentBuilder {
		private CaulfieldWarhammerBuilder() {
			equipment = new CaulfieldWarhammer();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 考尔菲德的战锤构建器
	 * @return 装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new CaulfieldWarhammerBuilder();
	}
}
