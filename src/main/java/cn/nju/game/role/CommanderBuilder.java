/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.role.builder.TargetBuilder;

/**
 * 召唤师建造器
 * @author frank
 *
 */
public interface CommanderBuilder extends TargetBuilder {

	/**
	 * 构建召唤师能量
	 * @param energy 能量值
	 * @return 召唤师构建器
	 */
	public CommanderBuilder energy(int energy);
	
	/**
	 * 构建召唤师经验值
	 * @param expirence 经验值
	 * @return 召唤师构建器
	 */
	public CommanderBuilder expirence(int expirence);

	/**
	 * 构建召唤师
	 * @return 召唤师角色
	 */
	public Commander build();

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#health(int)
	 */
	CommanderBuilder health(int health);

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#name(java.lang.String)
	 */
	CommanderBuilder name(String name);

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#physicalDamage(int)
	 */
	CommanderBuilder physicalDamage(int damage);

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#magicalDamage(int)
	 */
	CommanderBuilder magicalDamage(int damage);

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#armor(int)
	 */
	CommanderBuilder armor(int armor);

	/* (non-Javadoc)
	 * @see cn.nju.game.role.builder.TargetBuilder#magicalResistance(int)
	 */
	CommanderBuilder magicalResistance(int resistance);
}
