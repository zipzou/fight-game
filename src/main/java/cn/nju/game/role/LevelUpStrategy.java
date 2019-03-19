/**
 * 
 */
package cn.nju.game.role;

/**
 * 升级策略
 * @author frank
 *
 */
public interface LevelUpStrategy {

	/**
	 * 获取升级所需经验
	 * @param level 当前等级
	 * @return 下一级所需经验值
	 */
	public int getLevelupExperienceNeeded(int level);
	
	/**
	 * 初始经验值
	 */
	public static final int UNIT_LEVEL_EXPRIENCE = 100;
	
}
