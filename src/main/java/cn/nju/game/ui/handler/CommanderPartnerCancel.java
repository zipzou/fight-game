/**
 * 
 */
package cn.nju.game.ui.handler;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import cn.nju.game.service.SkillService;

/**
 * 第一个参与者取消
 * @author frank
 *
 */
public class CommanderPartnerCancel implements SkillCancel {

	private JList<String> context;
	private SkillService skillService;
	/* (non-Javadoc)
	 * @see cn.nju.game.ui.handler.SkillCancel#cancel()
	 */
	public void cancel() {
		DefaultListModel<String> skillModel = (DefaultListModel<String>) context.getModel();
		skillModel.remove(skillModel.size() - 1);
		context.revalidate();
		context.repaint();
		skillService.undoCompose();
	}
	/**
	 * @return the context
	 */
	public JList<String> getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(JList<String> context) {
		this.context = context;
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
