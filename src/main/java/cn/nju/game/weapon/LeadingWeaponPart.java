/**
 * 
 */
package cn.nju.game.weapon;

/**
 * 头部武器装饰部分
 * @author frank
 *
 */
public class LeadingWeaponPart implements DamageComputable {

	/**
	 * 伤害增强
	 */
	private int strengthDamageRate = 10;
	
	/**
	 * 武器
	 */
	private DamageComputable weapon;
	
	/**
	 * 装配的武器
	 * @param weapon
	 */
	public LeadingWeaponPart(DamageComputable weapon) {
		super();
		this.weapon = weapon;
	}

	/**
	 * @return the strengthDamageRate
	 */
	public int getStrengthDamageRate() {
		return strengthDamageRate;
	}

	/**
	 * @return the weapon
	 */
	public DamageComputable getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(DamageComputable weapon) {
		this.weapon = weapon;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.DamageComputable#computeDamage()
	 */
	public int computeDamage() {
		return (int) (strengthDamageRate + weapon.computeDamage());
	}

}
