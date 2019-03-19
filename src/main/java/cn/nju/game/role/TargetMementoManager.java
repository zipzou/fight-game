/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.fight.Memento;

/**
 * 目标备忘录管理者
 * @author frank
 *
 */
public class TargetMementoManager {
	/**
	 * 目标备忘录
	 */
	private Memento memento;

	/**
	 * @return the memento
	 */
	public Memento getMemento() {
		return memento;
	}

	/**
	 * @param memento the memento to set
	 */
	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
}
