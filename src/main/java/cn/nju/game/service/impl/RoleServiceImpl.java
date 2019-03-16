/**
 * 
 */
package cn.nju.game.service.impl;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WeaponVO;
import cn.nju.game.role.Commander;
import cn.nju.game.service.OnlineCommander;
import cn.nju.game.service.RoleService;

/**
 * @author frank
 *
 */
public class RoleServiceImpl implements RoleService {

	private Commander commander;
	
	public RoleServiceImpl(String name) {
		super();
		commander = OnlineCommander.sharedCommanders().get(name.trim());
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#createMagicianCommander()
	 */
	public void createMagicianCommander() {
		
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#createWarriorCommander()
	 */
	public void createWarriorCommander() {

	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#getCommanderBasic()
	 */
	public CommanderBasicVO getCommanderBasic() {
		if (null == commander) {
			return null;
		}
		CommanderBasicVO basicVO = commander.getBasicVO();
		return basicVO;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#getCommanderIcon()
	 */
	public void getCommanderIcon() {

	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#getWeaponVo()
	 */
	public WeaponVO getWeaponVo() {
		return commander.getWeaponVO();
	}
}
