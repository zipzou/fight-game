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
	 * 武器
	 */
	private DamageComputable weapon;
	/**
	 * 伤害减免比例
	 */
	private float damageReduceRate;
	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.DamageComputable#computeDamage()
	 */
	public int computeDamage() {
		return (int) ((1 - damageReduceRate) * weapon.computeDamage());
	}

}
