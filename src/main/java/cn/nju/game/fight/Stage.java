/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.role.StageFightMediator;
import cn.nju.game.role.StagePartner;
import cn.nju.game.role.StagePartnerMediator;

/**
 * 竞技场
 * @author frank
 *
 */
public class Stage implements FightStage {

	/**
	 * 决斗中介者
	 */
	private StagePartnerMediator mediator;
	/**
	 * 
	 */
	public Stage() {
		super();
		mediator = new StageFightMediator();
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.FightStage#registerPartner(cn.nju.game.role.StagePartner)
	 */
	public void registerPartner(StagePartner partner) {
		mediator.register(partner);
	}

}
