/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import cn.nju.game.model.vo.WeaponVO;
import cn.nju.game.service.RoleService;

/**
 * 加强武器头部的监听器
 * @author frank
 *
 */
public class StrengthWeaponLeadingListener extends MouseAdapter {

	private RoleService roleService;
	
	private JLabel observrLabel;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		WeaponVO weaponInfo = roleService.strengthWeaponHead();
		observrLabel.setToolTipText(weaponInfo.getDescription());
		observrLabel.revalidate();
		observrLabel.repaint();
	}

	/**
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the observrLabel
	 */
	public JLabel getObservrLabel() {
		return observrLabel;
	}

	/**
	 * @param observrLabel the observrLabel to set
	 */
	public void setObservrLabel(JLabel observrLabel) {
		this.observrLabel = observrLabel;
	}
	
}
