/**
 * 
 */
package cn.nju.game.equip;

/**
 * 装备池
 * @author frank
 *
 */
public interface EquipmentPool {

	/**
	 * 获取装备
	 * @param name 装备名
	 * @return 装备
	 */
	public Equipment getEquipment(String name);
	
	/**
	 * 获取所有装备
	 * @return 装备集合
	 */
	public Iterable<Equipment> getAllEquipments();
}
