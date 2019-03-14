/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DamageVO;
import cn.nju.game.weapon.DamageComputable;

/**
 * 武器伤害计算器
 * @author frank
 *
 */
public class WeaponDamageComputor implements DamageComputor {
	private DamageComputable weaponDamage;

	/**
	 * @param weaponDamage
	 */
	protected WeaponDamageComputor(DamageComputable weaponDamage) {
		super();
		this.weaponDamage = weaponDamage;
	}

	/**
	 * 
	 */
	protected WeaponDamageComputor() {
		super();
	}

	/**
	 * @return the weaponDamage
	 */
	public DamageComputable getWeaponDamage() {
		return weaponDamage;
	}

	/**
	 * @param weaponDamage the weaponDamage to set
	 */
	public void setWeaponDamage(DamageComputable weaponDamage) {
		this.weaponDamage = weaponDamage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		DamageVO damageVo = new DamageVO();
		int damageValue = weaponDamage.computeDamage();
		damageVo.setPhysicalDamage(damageValue); // 默认以物理伤害
		return damageVo;
	}
	
	
}
