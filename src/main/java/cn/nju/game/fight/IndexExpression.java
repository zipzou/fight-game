/**
 * 
 */
package cn.nju.game.fight;

/**
 * Map的索引表达式
 * @author frank
 *
 */
public interface IndexExpression {

	/**
	 * 在Map中索引
	 * @param index 索引键
	 * @return 索引到的结果
	 */
	public Object index(String index);
}
