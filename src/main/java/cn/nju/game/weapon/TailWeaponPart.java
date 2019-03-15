/**
 * 
 */
package cn.nju.game.weapon;

/**
 * 尾部武器配件，可为武器持有者提供基于武器伤害的减免效果
 * @author frank
 *
 */
public class TailWeaponPart implements DamageComputable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9130394229003249302L;
	/**
	 * 武器
	 */
	private DamageComputable weapon;
	/**
	 * 增强的伤害比例
	 */
	private float damageRate;
	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.DamageComputable#computeDamage()
	 */
	public int computeDamage() {
		return (int) ((damageRate + 1) * weapon.computeDamage());
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
	/**
	 * @return the damageRate
	 */
	public float getDamageRate() {
		return damageRate;
	}
	/**
	 * @param damageRate the damageRate to set
	 */
	public void setDamageRate(float damageRate) {
		this.damageRate = damageRate;
	}
	/**
	 * @param weapon
	 */
	public TailWeaponPart(DamageComputable weapon) {
		super();
		this.weapon = weapon;
	}
}
