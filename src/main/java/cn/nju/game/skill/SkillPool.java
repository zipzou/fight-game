/**
 * 
 */
package cn.nju.game.skill;

import java.util.List;

/**
 * 技能缓冲池
 * @author frank
 *
 */
public interface SkillPool {

	/**
	 * 获取技能
	 * @param name 技能名
	 * @param level 技能等级
	 * @return 缓冲的技能
	 */
	public Skill getSkill(String name, int level);
	
	/**
	 * 获取所有的技能
	 * @return 所有技能名
	 */
	public List<String> getAllSkills();
}
