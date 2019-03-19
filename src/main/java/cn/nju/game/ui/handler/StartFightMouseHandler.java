/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.service.StageService;
import cn.nju.game.service.impl.RoleServiceImpl;
import cn.nju.game.skill.SkillLeveledPool;
import cn.nju.game.ui.GameClientWindow;
import cn.nju.game.ui.MonsterConfigFrame;
import cn.nju.game.ui.util.BoundsUtil;

/**
 * @author frank
 *
 */
public class StartFightMouseHandler extends MouseAdapter {

	private JTable tableSkills;
	private JTable tableCommander;
	private List<String> equipments;
	private StageService stageService;
	private GameClientWindow context;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		int selectedRow = tableCommander.getSelectedRow();
		if (-1 < selectedRow) {
			String commanderName = tableCommander.getModel().getValueAt(selectedRow, 0).toString();
			JOptionPane.showMessageDialog(null, "接下来，请设置召唤师：" + commanderName + " 的对战信息", "对决提醒", JOptionPane.NO_OPTION);
			RoleServiceImpl targetService = new RoleServiceImpl(commanderName);
			GameClientWindow targetGameClient = new GameClientWindow(targetService, stageService);
			targetGameClient.setStageService(stageService);
			Rectangle bounds = BoundsUtil.getCenterOwnerBounds(800, 600);
			targetGameClient.setBounds(bounds);
			targetGameClient.setVisible(true);
			targetGameClient.setTitle("召唤师：" + commanderName);
			targetGameClient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			targetService.registerCommanderObserver(targetGameClient);
		} else {
			MonsterConfigFrame monsterConfigFrame = new MonsterConfigFrame(stageService);
			monsterConfigFrame.setTitle("配置野怪数");
			Rectangle centerBounds = BoundsUtil.getCenterOwnerBounds(monsterConfigFrame.getWidth(), monsterConfigFrame.getHeight());
			monsterConfigFrame.setBounds(centerBounds);
//			stageService.ready();
			monsterConfigFrame.setVisible(true);
			monsterConfigFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
		int[] selectedSkillsIndex = tableSkills.getSelectedRows();
		List<SkillVO> skills = new ArrayList<SkillVO>();
		for (int selectedSkillRow : selectedSkillsIndex) {
			String skillName = tableSkills.getModel().getValueAt(selectedSkillRow, 0).toString();
			int level = (Integer) tableSkills.getModel().getValueAt(selectedSkillRow, 1);
			SkillVO skillVO = new SkillVO(SkillLeveledPool.sharedPool().getSkill(skillName, level));
			skills.add(skillVO);
		}
		stageService.registerPartner(context.getLblCommanderName().getText(), equipments, skills); 
	}

	/**
	 * @return the tableSkills
	 */
	public JTable getTableSkills() {
		return tableSkills;
	}

	/**
	 * @param tableSkills the tableSkills to set
	 */
	public void setTableSkills(JTable tableSkills) {
		this.tableSkills = tableSkills;
	}

	/**
	 * @return the tableCommander
	 */
	public JTable getTableCommander() {
		return tableCommander;
	}

	/**
	 * @param tableCommander the tableCommander to set
	 */
	public void setTableCommander(JTable tableCommander) {
		this.tableCommander = tableCommander;
	}

	/**
	 * @return the equipments
	 */
	public List<String> getEquipments() {
		return equipments;
	}

	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(List<String> equipments) {
		this.equipments = equipments;
	}

	/**
	 * @return the stageService
	 */
	public StageService getStageService() {
		return stageService;
	}

	/**
	 * @param stageService the stageService to set
	 */
	public void setStageService(StageService stageService) {
		this.stageService = stageService;
	}

	/**
	 * @return the context
	 */
	public GameClientWindow getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(GameClientWindow context) {
		this.context = context;
	}
}
