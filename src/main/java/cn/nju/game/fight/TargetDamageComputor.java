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
public class TargetDamageComputor implements DamageComputor {

	private Target target;
	
	/**
	 * @param target
	 */
	protected TargetDamageComputor(Target target) {
		super();
		this.target = target;
	}

	/**
	 * 
	 */
	protected TargetDamageComputor() {
		super();
	}

	/**
	 * @return the commander
	 */
	public Target getCommander() {
		return target;
	}

	/**
	 * @param commander the commander to set
	 */
	public void setCommander(Target commander) {
		this.target = commander;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		int magicalDamage = target.getMagicDamage();
		int physicalDamage = target.getPhysicalDamage();
		DamageVO damage = new DamageVO();
		damage.setMagicalDamage(magicalDamage);
		damage.setPhysicalDamage(physicalDamage);
		return damage;
	}

}
