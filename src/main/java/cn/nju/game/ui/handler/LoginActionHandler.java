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
import cn.nju.game.ui.GameClientWindow;
import cn.nju.game.ui.util.BoundsUtil;

/**
 * @author frank
 *
 */
public class LoginActionHandler extends MouseAdapter {

	private JTextField commanderNameText;
	private JFrame parent;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		System.out.println(e);
		String name = commanderNameText.getText().trim();
		OnlineCommander.sharedCommanders().online(name);
		RoleServiceImpl roleService = new RoleServiceImpl(name);
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
		GameClientWindow gameClient = new GameClientWindow(roleService);
		gameClient.showInCenter(parent);
		parent.setVisible(false);
	}

	/**
	 * @return the commanderNameText
	 */
	public JTextField getCommanderNameText() {
		return commanderNameText;
	}

	/**
	 * @param commanderNameText the commanderNameText to set
	 */
	public void setCommanderNameText(JTextField commanderNameText) {
		this.commanderNameText = commanderNameText;
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
	
}
