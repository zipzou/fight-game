/**
 * 
 */
package cn.nju.game.weapon;

import cn.nju.game.model.vo.WeaponVO;

/**
 * 头部武器装饰部分
 * @author frank
 *
 */
public class LeadingWeaponPart extends Weapon implements DamageComputable, WeaponInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579182636428264271L;

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
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * @param strengthDamageRate the strengthDamageRate to set
	 */
	public void setStrengthDamageRate(int strengthDamageRate) {
		this.strengthDamageRate = strengthDamageRate;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.DamageComputable#computeDamage()
	 */
	public int computeDamage() {
		if (0 >= (int)strengthDamageRate) {
			return (int) ((1 + strengthDamageRate) * weapon.computeDamage());
		}
		return (int) (strengthDamageRate + weapon.computeDamage());
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

	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.Weapon#getDescription()
	 */
//	@Override
//	public String getDescription() {
//		description = name + "，将造成" + computeDamage() + "的自适应物理/魔法伤害";
//		return description;
//	}
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
}
