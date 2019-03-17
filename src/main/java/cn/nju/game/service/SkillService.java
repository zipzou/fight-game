/**
 * 
 */
package cn.nju.game.service;

import cn.nju.game.skill.ComposedSkill;

/**
 * 技能组合的服务桥接接口
 * @author frank
 *
 */
public interface SkillService {

	/**
	 * 添加技能组合
	 * @param skillName 技能名称
	 */
	public void addSkill(String skillName);
	
	/**
	 * 获取到组合技能
	 * @return 组合技能
	 */
	public ComposedSkill composedSkills();
}
