/**
 * 
 */
package cn.nju.game.equip;

/**
 * 锯齿短匕
 * @author frank
 *
 */
@Deprecated
public class SawtoothDagger extends ComposedEquipment {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2402399085529984649L;
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "锯齿短匕";
	
	private SawtoothDagger() {
		super();
	}

	/**
	 * 锯齿短匕构建器
	 * @author frank
	 *
	 */
	private static class SawtoothDaggerBuilder extends AbstractComposedEquipmentBuilder {
		private SawtoothDaggerBuilder() {
			equipment = new SawtoothDagger();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 锯齿短匕构建器
	 * @return 装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new SawtoothDaggerBuilder();
	}
}
