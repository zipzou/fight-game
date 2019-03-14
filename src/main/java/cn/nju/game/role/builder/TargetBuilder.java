/**
 * 
 */
package cn.nju.game.role.builder;

import cn.nju.game.role.Target;

/**
 * 召唤师创建者
 * @author frank
 *
 */
public interface TargetBuilder {

//	public 
	/**
	 * 构建目标
	 * @return 已构建的目标
	 */
	public Target build();
	
	/**
	 * 构建目标的健康值
	 * @param health 健康值
	 * @return 目标建造器
	 */
	public TargetBuilder health(int health);
	
	/**
	 * 构建目标的名称
	 * @param name 目标名称
	 * @return 目标建造器
	 */
	public TargetBuilder name(String name);
	
	/**
	 * 构建目标的物理伤害值
	 * @param damage 伤害值
	 * @return 目标建造器
	 */
	public TargetBuilder physicalDamage(int damage);
	
	/**
	 * 构建目标的魔法伤害值
	 * @param damage 伤害值
	 * @return 目标建造器
	 */
	public TargetBuilder magicalDamage(int damage);
	
	/**
	 * 构建目标的护甲值
	 * @param armor 护甲值
	 * @return 目标建造器
	 */
	public TargetBuilder armor(int armor);
	
	/**
	 * 构建目标魔法抗性
	 * @param resistance 魔法抗性值
	 * @return 目标建造器
	 */
	public TargetBuilder magicalResistance(int resistance);
}
