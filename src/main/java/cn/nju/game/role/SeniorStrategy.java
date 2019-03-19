/**
 * 
 */
package cn.nju.game.role;

/**
 * 高级升级策略
 * @author frank
 *
 */
public class SeniorStrategy implements LevelUpStrategy {

	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelUpStrategy#getLevelupExperienceNeeded(int)
	 */
	public int getLevelupExperienceNeeded(int level) {
		return (int) (UNIT_LEVEL_EXPRIENCE * Math.pow(2.0, level + 1));
	}

}
