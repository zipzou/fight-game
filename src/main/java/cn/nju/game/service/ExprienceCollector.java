/**
 * 
 */
package cn.nju.game.service;

/**
 * 经验收集器
 * @author frank
 *
 */
public interface ExprienceCollector {

	/**
	 * 获取当前收集到到经验值
	 * @param name 对象名
	 * @return 经验值
	 */
	public int getExprience(String name);
	
	/**
	 * 获取当前管理对象的等级
	 * @param name 对象名
	 * @return 对象等级信息
	 */
	public int getLevel(String name);
}
