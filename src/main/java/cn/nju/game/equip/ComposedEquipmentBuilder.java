/**
 * 
 */
package cn.nju.game.equip;

/**
 * 合成装备构建器
 * @author frank
 *
 */
public interface ComposedEquipmentBuilder extends EquipmentBuilder {

	/**
	 * 添加子装备
	 * @param equipment 装备
	 */
	public ComposedEquipmentBuilder add(Equipment equipment);
	
}
