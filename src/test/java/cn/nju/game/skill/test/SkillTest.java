/**
 * 
 */
package cn.nju.game.skill.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillLeveledPool;

/**
 * @author frank
 *
 */
public class SkillTest {

	@Test
	public void testSkill() {
		Skill skill = SkillLeveledPool.sharedPool().getSkill("无懈可击", 2);
		assertTrue(null != skill);
	}
	
	@Test
	public void testGetAllSkillNames() {
		List<String> skills = SkillLeveledPool.sharedPool().getAllSkills();
		for (String string : skills) {
			System.out.println(string);
		}
	}
}
