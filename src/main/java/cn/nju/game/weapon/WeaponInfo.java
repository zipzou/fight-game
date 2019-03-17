/**
 * 
 */
package cn.nju.game.weapon;

import cn.nju.game.model.vo.WeaponVO;

/**
 * @author frank
 *
 */
public interface WeaponInfo {
	/**
	 * @return the name
	 */
	public String getName();
	
	/**
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * 获取武器信息
	 * @return 武器信息
	 */
	public WeaponVO getWeaponInfo();
}
