/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.nju.game.service.SkillService;
import cn.nju.game.ui.FightStageFrame;
import cn.nju.game.ui.state.GameOverState;

/**
 * 攻击执行指令
 * @author frank
 *
 */
public class AttackHandler extends MouseAdapter {

	private FightStageFrame context;
	private int index;
	private SkillService skillService;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (context.getCurState() instanceof GameOverState) {
			return;
		}
		context.getStageService().attack(index, skillService);
		context.nextState();
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

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the skillService
	 */
	public SkillService getSkillService() {
		return skillService;
	}

	/**
	 * @param skillService the skillService to set
	 */
	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
}
