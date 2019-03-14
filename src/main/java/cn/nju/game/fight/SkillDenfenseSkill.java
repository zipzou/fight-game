/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DefenceVO;
import cn.nju.game.skill.Skill;

/**
 * 技能防御
 * @author frank
 *
 */
public class SkillDenfenseSkill implements DefenceComputor {
	private Skill skill;
	/**
	 * @param skill
	 */
	protected SkillDenfenseSkill(Skill skill) {
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
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		return new DefenceVO();
	}

}
