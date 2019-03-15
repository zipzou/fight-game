/**
 * 
 */
package cn.nju.game.weapon;

import java.io.Serializable;

/**
 * 可计算伤害的接口
 * @author frank
 *
 */
public interface DamageComputable extends Serializable {
	/**
	 * 计算伤害
	 * @return 伤害值
	 */
	public int computeDamage();
}
