/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.equip.Bag;
import cn.nju.game.skill.Skill;
import cn.nju.game.weapon.DamageComputable;
import cn.nju.game.weapon.Weapon;

/**
 * 抽象被攻击类，使子类有选择实现
 * @author frank
 *
 */
@Deprecated
public class AbstractAttackedPartner implements Attacked {

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacked#attacked(cn.nju.game.role.Target, cn.nju.game.weapon.Weapon)
	 */
	public void attacked(Target source, Weapon weapon) {
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacked#attacked(cn.nju.game.role.Target)
	 */
	public void attacked(Target source) {
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacked#attacked(cn.nju.game.role.Target, cn.nju.game.weapon.DamageComputable, cn.nju.game.equip.Bag, cn.nju.game.skill.Skill)
	 */
	public void attacked(Target source, DamageComputable weapon, Bag equipments, Skill skill) {
		
	}
}
