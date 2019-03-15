/**
 * 
 */
package cn.nju.game.skill;

/**
 * 组合技能
 * @author frank
 *
 */
public interface ComposedSkill extends Skill {

	/**
	 * 添加技能
	 * @param skill 技能
	 */
	public void add(Skill skill);
	
	/**
	 * 撤销技能
	 * @return 已撤销的技能
	 */
	public Skill cancelSkill();
	
}
