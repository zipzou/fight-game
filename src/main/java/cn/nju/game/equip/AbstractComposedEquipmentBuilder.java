/**
 * 
 */
package cn.nju.game.equip;

/**
 * 抽象组合装备构建者
 * @author frank
 *
 */
@Deprecated
public class AbstractComposedEquipmentBuilder extends AbstractEquipmentBuilder implements ComposedEquipmentBuilder {

	protected ComposedEquipment composedEquipment;
	/* (non-Javadoc)
	 * @see cn.nju.game.equip.ComposedEquipmentBuilder#add(cn.nju.game.equip.Equipment)
	 */
	public ComposedEquipmentBuilder add(Equipment equipment) {
		composedEquipment.subEquipments.add(equipment);
		return this;
	}

}
