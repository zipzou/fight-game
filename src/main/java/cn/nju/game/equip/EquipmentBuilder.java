/**
 * 
 */
package cn.nju.game.equip;

/**
 * 装备构建器
 * @author frank
 *
 */
public interface EquipmentBuilder {
	
	/**
	 * 构建装备
	 * @return 构建好的装备
	 */
	public Equipment build();
	
	/**
	 * 构建描述
	 * @param desc 装备描述信息
	 * @return 装备构建器
	 */
	public EquipmentBuilder description(String desc);
	
	/**
	 * 构建装备物理伤害
	 * @param damage 物理伤害值
	 * @return 装备构建器
	 */
	public EquipmentBuilder physicalDamage(int damage);
	
	/**
	 * 构建装备的魔法伤害
	 * @param damage 魔法伤害值
	 * @return 装备构建器
	 */
	public EquipmentBuilder magicalDamage(int damage);
	
	/**
	 * 构建装备带来的护甲值
	 * @param armor 护甲值
	 * @return 装备构建器
	 */
	public EquipmentBuilder armor(int armor);
	
	/**
	 * 构建装备魔法抗性
	 * @param resistance 魔法抗性值
	 * @return 装备构建器
	 */
	public EquipmentBuilder magicalResistance(int resistance);
	
	/**
	 * 构建装备价格
	 * @param price 价格
	 * @return 装备构建器
	 */
	public EquipmentBuilder price(int price);
	
	/**
	 * 构建装备血量提升
	 * @param health 血量提升值
	 * @return 装备构建器
	 */
	public EquipmentBuilder strengthHealth(int health);
	
	/**
	 * 构建装备回血率
	 * @param rate 回血率
	 * @return 装备构建器
	 */
	public EquipmentBuilder recoverHealth(float rate);
	
	/**
	 * 构建装备能量恢复率
	 * @param rate 能量恢复率
	 */
	public EquipmentBuilder recoverEnergy(float rate);
	
	/**
	 * 构建装备名称
	 * @param name 装备名称
	 * @return 装备构建器
	 */
	public EquipmentBuilder name(String name);
	
	/**
	 * 添加子装备
	 * @param equipment 子装备
	 * @return 装备构建器
	 */
	public EquipmentBuilder add(Equipment equipment);
}
