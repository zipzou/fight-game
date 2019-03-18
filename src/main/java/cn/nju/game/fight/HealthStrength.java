/**
 * 
 */
package cn.nju.game.fight;

/**
 * 生命值提高
 * @author frank
 *
 */
public interface HealthStrength {

	/**
	 * 计算生命值加成
	 * @return 生命值加成值
	 */
	public int computeHealthImproved();
	
	/**
	 * 计算生命值回复率
	 * @return 生命值回复率
	 */
	public float computeHealthRecoverRate();
	
}
