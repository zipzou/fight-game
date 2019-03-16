package cn.nju.game.skill;

import java.io.Serializable;

import cn.nju.game.fight.MagicalDamage;
import cn.nju.game.fight.MagicalDenfence;
import cn.nju.game.fight.PhysicalDamage;
import cn.nju.game.fight.PhysicalDefence;
import cn.nju.game.role.Target;

/**
 * 召唤师技能
 */
public interface Skill extends PhysicalDamage, PhysicalDefence, MagicalDamage, MagicalDenfence, Serializable {

    /**
     * 施法
     * @param target 将要施法的对象
     */
    public void conjure(Target target);
    
    /**
     * 获取技能等级
     * @return 技能等级值
     */
    public int getLevel();
    
//  * @param physicalDamage 物理伤害提升效果
//  * @param armor 护甲值提升
//  * @param magicalDamage 魔法伤害提升
//  * @param magicalResistance 魔法抗性提升
    /**
     * 技能升级
     * @return 升级后的技能
     */
    public Skill upgrade();

    /**
     * 获取需要的能量值
     * @return 所需能量值
     */
    public int getEnergyNeeded();

    /**
     * 获取技能名
     * @return 技能名称
     */
	public String getName();
	
	/**
	 * 获取技能描述信息
	 * @return 技能描述信息
	 */
	public String getDescription();
}