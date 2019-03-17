/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DamageVO;
import cn.nju.game.skill.Skill;

/**
 * 技能伤害计算
 * @author frank
 *
 */
public class SkillDamageComputor implements DamageComputor {
	private Skill skill;
	/**
	 * @param skill
	 */
	protected SkillDamageComputor(Skill skill) {
		super();
		this.skill = skill;
	}
	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}
	/**
	 * @param skill the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		if (null == skill) {
			return new DamageVO();
		}
		int physicalDamage = skill.computePhysicalDamage();
		int magicDamage = skill.computeMagicDamage();
		DamageVO damage = new DamageVO();
		damage.setPhysicalDamage(physicalDamage);
		damage.setMagicalDamage(magicDamage);
		return damage;
	}

}
