/**
 * 
 */
package cn.nju.game.service;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WeaponVO;

/**
 * 角色服务
 * @author frank
 *
 */
public interface RoleService {

	public void createMagicianCommander();
	
	public void createWarriorCommander();
	
	public CommanderBasicVO getCommanderBasic();
	
	public String getCommanderIcon();
	
	public WeaponVO getWeaponVo();
	
	public WeaponVO strengthWeaponHead();
	
	public WeaponVO strengthWeaponTail();
}
