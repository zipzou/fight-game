/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.nju.game.service.OnlineCommander;
import cn.nju.game.service.impl.RoleServiceImpl;
import cn.nju.game.service.impl.StageServiceImpl;
import cn.nju.game.ui.GameClientWindow;
import cn.nju.game.ui.util.BoundsUtil;

/**
 * @author frank
 *
 */
public class LoginActionHandler extends MouseAdapter {

	private JTextField commanderNameText1;
	private JTextField commanderNameText2;
	private int loginMode;
	private JFrame parent;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		loginMode = 1;
		if (1 == loginMode) {
			String name = commanderNameText1.getText().trim();
			String[] allNames = name.split(";");
			for (String curName : allNames) {
				OnlineCommander.sharedCommanders().online(curName);
			}
			RoleServiceImpl roleService = new RoleServiceImpl(allNames[0]);
			if (null == roleService.getCommanderBasic()) {
				JDialog dialog = new JDialog(parent);
				dialog.setTitle("未找到角色");
				JLabel contentField = new JLabel("未找到角色信息，请添加角色再进入！");
				dialog.getContentPane().add(contentField);
				int w = 240;
				int h = 100;
				dialog.setBounds(BoundsUtil.getCenterOwnerBounds(parent, w, h));
				dialog.setMaximumSize(new Dimension(w, h));
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				return;
			}
			StageServiceImpl stageService = new StageServiceImpl();
			GameClientWindow gameClient = new GameClientWindow(roleService, stageService);
			roleService.registerCommanderObserver(gameClient);
			gameClient.showInCenter(parent);
			gameClient.setTitle("召唤师：" + allNames[0]);
		} else if (2 == loginMode) {
			String name1 = commanderNameText1.getText().trim();
			OnlineCommander.sharedCommanders().online(name1);
			RoleServiceImpl roleService1 = new RoleServiceImpl(name1);
			if (null == roleService1.getCommanderBasic()) {
				JDialog dialog = new JDialog(parent);
				dialog.setTitle("未找到角色");
				JLabel contentField = new JLabel("未找到角色信息: " + name1 + "请添加角色再进入！");
				dialog.getContentPane().add(contentField);
				int w = 240;
				int h = 100;
				dialog.setBounds(BoundsUtil.getCenterOwnerBounds(parent, w, h));
				dialog.setMaximumSize(new Dimension(w, h));
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				return;
			}
			StageServiceImpl stageService = new StageServiceImpl();
			GameClientWindow gameClient1 = new GameClientWindow(roleService1, stageService);
			gameClient1.setStageService(new StageServiceImpl());
			gameClient1.setTitle("召唤师：" + name1);
			
			String name2 = commanderNameText2.getText().trim();
			OnlineCommander.sharedCommanders().online(name2);
			RoleServiceImpl roleService2 = new RoleServiceImpl(name2);
			if (null == roleService1.getCommanderBasic()) {
				JDialog dialog = new JDialog(parent);
				dialog.setTitle("未找到角色");
				JLabel contentField = new JLabel("未找到角色信息: " + name2 + "请添加角色再进入！");
				dialog.getContentPane().add(contentField);
				int w = 240;
				int h = 100;
				dialog.setBounds(BoundsUtil.getCenterOwnerBounds(parent, w, h));
				dialog.setMaximumSize(new Dimension(w, h));
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				return;
			}
			GameClientWindow gameClient2 = new GameClientWindow(roleService2, stageService);
			gameClient2.setTitle("召唤师：" + name2);
			gameClient2.setStageService(new StageServiceImpl());

			gameClient1.showInCenter(parent);
			gameClient2.showInCenter(parent);
		}
		parent.dispose();
	}

	/**
	 * @return the parent
	 */
	public JFrame getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(JFrame parent) {
		this.parent = parent;
	}

	/**
	 * @return the commanderNameText1
	 */
	public JTextField getCommanderNameText1() {
		return commanderNameText1;
	}

	/**
	 * @param commanderNameText1 the commanderNameText1 to set
	 */
	public void setCommanderNameText1(JTextField commanderNameText1) {
		this.commanderNameText1 = commanderNameText1;
	}

	/**
	 * @return the commanderNameText2
	 */
	public JTextField getCommanderNameText2() {
		return commanderNameText2;
	}

	/**
	 * @param commanderNameText2 the commanderNameText2 to set
	 */
	public void setCommanderNameText2(JTextField commanderNameText2) {
		this.commanderNameText2 = commanderNameText2;
	}

	/**
	 * @return the loginMode
	 */
	public int getLoginMode() {
		return loginMode;
	}

	/**
	 * @param loginMode the loginMode to set
	 */
	public void setLoginMode(int loginMode) {
		this.loginMode = loginMode;
	}
	
}
