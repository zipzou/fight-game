/**
 * 
 */
package cn.nju.game.ui.state;

/**
 * 当前战斗状态
 * @author frank
 *
 */
public interface FightState {
	/**
	 * 更新部分UI
	 */
	public void update();
	
	/**
	 * 下一个状态
	 */
	public void next();
	
	/**
	 * 立即切换游戏结束的状态
	 */
	public void toGameOverState();
}
