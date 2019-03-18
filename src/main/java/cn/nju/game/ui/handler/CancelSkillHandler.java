/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 撤销技能处理类
 * @author frank
 *
 */
public class CancelSkillHandler extends MouseAdapter {

	private SkillCancel cancel;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		cancel.cancel();
	}

	/**
	 * @return the cancel
	 */
	public SkillCancel getCancel() {
		return cancel;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(SkillCancel cancel) {
		this.cancel = cancel;
	}

}
