/**
 * 
 */
package cn.nju.game.fight;

/**
 * 产生物理伤害抗性
 * @author frank
 *
 */
public interface PhysicalDefence {

	/**
	 * 产生物理伤害防御值
	 * @return 护甲值
	 */
	public int computeArmor();
}
