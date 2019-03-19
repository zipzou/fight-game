/**
 * 
 */
package cn.nju.game.ui.state;

import javax.swing.JOptionPane;

import cn.nju.game.ui.FightStageFrame;

/**
 * 游戏结束状态
 * @author frank
 *
 */
public class GameOverState implements FightState {
	private FightStageFrame context;
	private boolean overed;
	public GameOverState(FightStageFrame context) {
		super();
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#update()
	 */
	public void update() {
		if (overed) {
			return ;
		}
		JOptionPane.showMessageDialog(context, "恭喜你，" + context.getStageService().getWinner().getName() + "，你赢得了这场比赛！", "Game Over", JOptionPane.PLAIN_MESSAGE);
//		context.setVisible(false);
		overed = true;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#next()
	 */
	public void next() {
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.state.FightState#toGameOverState()
	 */
	public void toGameOverState() {
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
}
