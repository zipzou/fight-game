/**
 * 
 */
package cn.nju.game.role;

/**
 * 对象的备忘录
 * @author frank
 *
 */
public interface Mementor {

	/**
	 * 创建备忘录
	 * @return 备忘录对象
	 */
	public <T> T createMemento();
	
	/**
	 * 从备忘录对象中还原
	 * @param memento 备忘录对象
	 */
	public void restoreMemento(Object memento);
}
