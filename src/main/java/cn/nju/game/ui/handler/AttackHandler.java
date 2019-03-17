/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.nju.game.ui.FightStageFrame;

/**
 * 攻击执行指令
 * @author frank
 *
 */
public class AttackHandler extends MouseAdapter {

	private FightStageFrame context;
	private int index;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (1 == index) {
			context.getStageService().attack(index, context.getSkillServiceForFir());
		} else if (2 == index) {
			context.getStageService().attack(index, context.getSkillServiceForSec());
		}
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
}
