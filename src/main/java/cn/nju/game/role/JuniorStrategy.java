/**
 * 
 */
package cn.nju.game.role;

/**
 * 初级升级方案
 * @author frank
 *
 */
public class JuniorStrategy implements LevelUpStrategy {

	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelUpStrategy#getLevelupExperienceNeeded(int)
	 */
	public int getLevelupExperienceNeeded(int level) {
		return (int) (UNIT_LEVEL_EXPRIENCE * Math.pow(1.5, level + 1));
	}

}
