/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.equip.Bag;
import cn.nju.game.skill.Skill;
import cn.nju.game.weapon.DamageComputable;

/**
 * 被攻击接口
 * @author frank
 *
 */
public interface Attacked {

	/**
	 * 遭受攻击
	 * @param source 攻击来源
	 * @param weapon 攻击武器
	 * @param equipments 攻击装备
	 * @param skill 施放的技能
	 */
	public void attacked(Target source, DamageComputable weapon, Bag equipments, Skill skill);
//	
//	/**
//	 * 遭受攻击
//	 * @param source 攻击来源
//	 * @param weapon 攻击武器
//	 */
//	public void attacked(Target source, Weapon weapon);
//	
//	/**
//	 * 遭受攻击
//	 * @param source 攻击来源
//	 */
//	public void attacked(Target source);
	
}
