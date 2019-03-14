/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DamageVO;
import cn.nju.game.role.Target;

/**
 * 召唤师伤害计算
 * @author frank
 *
 */
public class CommanderDamageComputor implements DamageComputor {

	private Target commander;
	
	/**
	 * @return the commander
	 */
	public Target getCommander() {
		return commander;
	}

	/**
	 * @param commander the commander to set
	 */
	public void setCommander(Target commander) {
		this.commander = commander;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		int magicalDamage = commander.getMagicDamage();
		int physicalDamage = commander.getPhysicalDamage();
		DamageVO damage = new DamageVO();
		damage.setMagicalDamage(magicalDamage);
		damage.setPhysicalDamage(physicalDamage);
		return damage;
	}

}
