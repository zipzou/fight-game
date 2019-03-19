/**
 * 
 */
package cn.nju.game.service;

import java.util.Observer;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WeaponVO;
import cn.nju.game.role.Commander;

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
	
	/**
	 * 获取当前经验值
	 * @param target 当前目标
	 * @return 经验值
	 */
	public int getCurrentExprience(Commander target);
	
	/**
	 * 注册召唤师监听者
	 * @param o 监听者
	 */
	public void registerCommanderObserver(Observer o);
}
