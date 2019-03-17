/**
 * 
 */
package cn.nju.game.weapon;

import cn.nju.game.model.vo.WeaponVO;

/**
 * 尾部武器配件，可为武器持有者提供基于武器伤害的减免效果
 * @author frank
 *
 */
public class TailWeaponPart extends Weapon implements DamageComputable, WeaponInfo {
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
	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.Weapon#getDescription()
	 */

	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.WeaponInfo#getWeaponInfo()
	 */
	public WeaponVO getWeaponInfo() {
		if (weapon instanceof WeaponInfo) {
			WeaponVO weaponInfo = ((WeaponInfo) weapon).getWeaponInfo();
			weaponInfo.setName(getName());
			weaponInfo.setDamage(getDamage());
			weaponInfo.setPrice(getPrice());
			weaponInfo.setDescription(getDescription());
			return weaponInfo;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.Weapon#getName()
	 */
	@Override
	public String getName() {
		if (weapon instanceof Weapon) {
			return ((Weapon) weapon).getName();
		}
		return super.getName();
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.Weapon#getDamage()
	 */
	@Override
	public int getDamage() {
		if (weapon instanceof Weapon) {
			return damage + ((Weapon) weapon).getDamage();
		}
		return super.getDamage();
	}
}
