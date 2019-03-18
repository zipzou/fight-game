/**
 * 
 */
package cn.nju.game.ui.state;

import java.awt.Component;

import cn.nju.game.ui.FightStageFrame;
import cn.nju.game.ui.util.ComponentSwitchUtil;

/**
 * 野怪操作状态
 * @author frank
 *
 */
public class MonsterOperationState implements FightState {
	private FightStageFrame context;
	public MonsterOperationState(FightStageFrame context) {
		super();
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#update()
	 */
	public void update() {
		Component[] components = context.getContentPane().getComponents();
		for (int i = 0; i < components.length; i++) {
			ComponentSwitchUtil.disableUi(components[i]);
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#next()
	 */
	public void next() {
		context.setCurState(new FirstPartnerOperationState(context));
	}

	/**
	 * @return the context
	 */
	public FightStageFrame getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(FightStageFrame context) {
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#toGameOverState()
	 */
	public void toGameOverState() {
		this.context.setCurState(new GameOverState(this.context));		
	}
}
