/**
 * 
 */
package cn.nju.game.service.impl;

import java.io.IOException;
import java.util.Observer;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WeaponVO;
import cn.nju.game.role.Commander;
import cn.nju.game.service.OnlineCommander;
import cn.nju.game.service.RoleService;
import cn.nju.game.util.ObjectiveSerializeUtil;
import cn.nju.game.weapon.LeadingWeaponPart;
import cn.nju.game.weapon.TailWeaponPart;
import cn.nju.game.weapon.Weapon;
import cn.nju.game.weapon.WeaponInfo;

/**
 * @author frank
 *
 */
public class RoleServiceImpl implements RoleService {

	private Commander commander;
	
	private Weapon weapon;
	
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
	public String getCommanderIcon() {
		return getCommanderBasic().getIcon();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#getWeaponVo()
	 */
	public WeaponVO getWeaponVo() {
		if (null == weapon) {
			weapon = commander.getWeapon();
		}
		return weapon.getWeaponInfo();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#strengthWeaponHead()
	 */
	public WeaponVO strengthWeaponHead() {
		if (null != weapon) {
			weapon = new LeadingWeaponPart(weapon);
		} else {
			weapon = commander.getWeapon();
			weapon = new LeadingWeaponPart(weapon);
		}
		((LeadingWeaponPart) weapon).setStrengthDamageRate(10);
		try {
			ObjectiveSerializeUtil.serialize(GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString(), getCommanderBasic().getName(), weapon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (weapon instanceof WeaponInfo) {
			return ((WeaponInfo) weapon).getWeaponInfo();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#strengthWeaponTail()
	 */
	public WeaponVO strengthWeaponTail() {
		if (null != weapon) {
			weapon = new TailWeaponPart(weapon);
		} else {
			weapon = commander.getWeapon();
			weapon = new TailWeaponPart(weapon);
		}
		((TailWeaponPart) weapon).setDamageRate(0.2f);
		try {
			ObjectiveSerializeUtil.serialize(GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString(), getCommanderBasic().getName(), weapon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (weapon instanceof WeaponInfo) {
			return ((WeaponInfo) weapon).getWeaponInfo();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#getCurrentExprience(cn.nju.game.role.Target)
	 */
	public int getCurrentExprience(Commander target) {
		return target.getExpirience();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.RoleService#registerCommanderObserver(java.util.Observer)
	 */
	public void registerCommanderObserver(Observer o) {
		commander.addObserver(o);
	}
}
