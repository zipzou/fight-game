/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.role.StagePartner;

/**
 * 决斗场
 * @author frank
 *
 */
public interface FightStage {

	/**
	 * 注册参与者
	 * @param partner 参与者，用于参与决斗活动
	 */
	public void registerPartner(StagePartner partner);
	
}
