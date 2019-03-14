/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cn.nju.game.ui.RoleCreateFrame;

/**
 * @author frank
 *
 */
public class CreateRoleActionHandler implements ActionListener {

	private JFrame ownerFrame;
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		RoleCreateFrame roleCreateFrame = new RoleCreateFrame();
		roleCreateFrame.showInCenter(ownerFrame);
	}

	/**
	 * @return the ownerFrame
	 */
	public JFrame getOwnerFrame() {
		return ownerFrame;
	}

	/**
	 * @param ownerFrame the ownerFrame to set
	 */
	public void setOwnerFrame(JFrame ownerFrame) {
		this.ownerFrame = ownerFrame;
	}

}
