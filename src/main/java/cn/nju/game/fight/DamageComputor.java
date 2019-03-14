/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DamageVO;

/**
 * 伤害计算器
 * @author frank
 *
 */
public interface DamageComputor {

	/**
	 * 伤害计算
	 * @return 伤害值
	 */
	public DamageVO compute();
	
}
