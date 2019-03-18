/**
 * 
 */
package cn.nju.game.ui.state;

import java.awt.Component;

import cn.nju.game.ui.FightStageFrame;
import cn.nju.game.ui.util.ComponentSwitchUtil;

/**
 * 第二个召唤师操作状态
 * @author frank
 *
 */
public class SecondPartnerOperationState implements FightState {
	private FightStageFrame context;
	
	public SecondPartnerOperationState(FightStageFrame context) {
		super();
		this.context = context;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#update()
	 */
	public void update() {
		Component[] components = context.getContentPane().getComponents();
		for (int i = 0; i < components.length; i++) {
			if (1 != i) {
				ComponentSwitchUtil.disableUi(components[i]);
			} else {
				ComponentSwitchUtil.enableUi(components[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#next()
	 */
	public void next() {
		context.setCurState(new MonsterOperationState(context));
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
