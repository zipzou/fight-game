/**
 * 
 */
package cn.nju.game.role;

/**
 * @author frank
 *
 */
public class PrimaryLevelupStrategy implements LevelUpStrategy {

	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelUpStrategy#getLevelupExperienceNeeded()
	 */
	public int getLevelupExperienceNeeded(int level) {
		return (int) (Math.pow(1.2, level + 1) * UNIT_LEVEL_EXPRIENCE);
	}

}
