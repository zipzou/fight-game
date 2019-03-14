/**
 * 
 */
package cn.nju.game.service;

import cn.nju.game.model.vo.CommanderBasicVO;

/**
 * @author frank
 *
 */
public interface RoleService {

	public void createMagicianCommander();
	
	public void createWarriorCommander();
	
	public CommanderBasicVO getCommanderBasic();
	
	public void getCommanderIcon();
}
