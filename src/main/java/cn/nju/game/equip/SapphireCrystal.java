/**
 * 
 */
package cn.nju.game.equip;

/**
 * 蓝水晶
 * @author frank
 *
 */
@Deprecated
public class SapphireCrystal extends Equipment {
	/**
	 * 装备名称
	 */
	private static final String EQUIPMENT_NAME = "蓝水晶";
	
	private SapphireCrystal() {
		super();
	}

	/**
	 * 蓝水晶构建器
	 * @author frank
	 *
	 */
	private static class SapphireCrystalBuilder extends AbstractEquipmentBuilder {
		private SapphireCrystalBuilder() {
			equipment = new SapphireCrystal();
			equipment.name = EQUIPMENT_NAME;
		}
	}
	
	/**
	 * 蓝水晶构建器
	 * @return 装备构建器
	 */
	public static EquipmentBuilder builder() {
		return new SapphireCrystalBuilder();
	}
}
