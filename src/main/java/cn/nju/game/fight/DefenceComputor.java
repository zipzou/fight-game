/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DefenceVO;

/**
 * 防御指标计算接口
 * @author frank
 *
 */
public interface DefenceComputor {

	/**
	 * 计算防御值
	 * @return 防御值
	 */
	public DefenceVO compute();
	
}
