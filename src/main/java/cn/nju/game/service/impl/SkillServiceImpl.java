/**
 * 
 */
package cn.nju.game.service.impl;

import cn.nju.game.service.SkillService;
import cn.nju.game.skill.ComposedSkill;
import cn.nju.game.skill.SkillGroup;
import cn.nju.game.skill.SkillLeveledPool;

/**
 * 组合技能服务实现
 * @author frank
 *
 */
public class SkillServiceImpl implements SkillService {

	private ComposedSkill skillGroup;
	
	public SkillServiceImpl() {
		super();
		skillGroup = new SkillGroup();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.SkillService#addSkill(java.lang.String)
	 */
	public void addSkill(String skillName, int level) {
		skillGroup.add(SkillLeveledPool.sharedPool().getSkill(skillName, level));
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.SkillService#composedSkills()
	 */
	public ComposedSkill composedSkills() {
		return skillGroup;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.SkillService#undoCompose()
	 */
	public void undoCompose() {
		skillGroup.cancelSkill();
	}
}
