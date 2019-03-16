/**
 * 
 */
package cn.nju.game.service;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WeaponVO;

/**
 * @author frank
 *
 */
public interface RoleService {

	public void createMagicianCommander();
	
	public void createWarriorCommander();
	
	public CommanderBasicVO getCommanderBasic();
	
	public void getCommanderIcon();
	
	public WeaponVO getWeaponVo();
	
}
