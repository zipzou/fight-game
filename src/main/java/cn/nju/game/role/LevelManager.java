/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;

/**
 * 等级管理者，用于对召唤师进行升级
 * @author frank
 *
 */
public interface LevelManager {
	/**
	 * 处理经验值
	 * @param collector 经验管理员
	 */
	public void handleExprience(ExprienceCollector collector);
	
	/**
	 * 获得该管理者所管理对象的名称
	 * @return 被管理对象名称
	 */
	public String forName();
	
	/**
	 * 获取升级策略
	 * @return
	 */
	public LevelUpStrategy getStrategy();
	
	/**
	 * 初始经验值
	 */
	public static final int UNIT_LEVEL_EXPRIENCE = 100;
}
