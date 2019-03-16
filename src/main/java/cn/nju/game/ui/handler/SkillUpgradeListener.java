/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillLeveledPool;
import cn.nju.game.ui.util.ModelToTableModelUtil;
import cn.nju.game.ui.util.SharedModelUtilFactory;
import cn.nju.game.ui.util.SkillModelUtilFactory;

/**
 * @author frank
 *
 */
public class SkillUpgradeListener implements ActionListener {
	private JTable context;
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		int selectedSkillIndex = context.getSelectedRow();
		TableModel oldModel = context.getModel();
		Object level = oldModel.getValueAt(context.getSelectedRow(), 1);
		Skill upgradedLevel = SkillLeveledPool.sharedPool().getSkill(oldModel.getValueAt(selectedSkillIndex, 0).toString(), (Integer) level);
		upgradedLevel = upgradedLevel.upgrade();
		try {
			ModelToTableModelUtil skillUtil = SharedModelUtilFactory.sharedFactory().getUtil(SkillModelUtilFactory.class.getName());
			SkillVO skillVO = new SkillVO(upgradedLevel);
			Object[] skillValues = skillUtil.transferModelToValues(skillVO);
			for (int i = 0; i < skillValues.length; i++) {
				oldModel.setValueAt(skillValues[i], selectedSkillIndex, i);
			}
			context.revalidate();
			context.repaint();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * @return the context
	 */
	public JTable getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(JTable context) {
		this.context = context;
	}
	
}
