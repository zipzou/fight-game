package cn.nju.game.equip;

import java.io.Serializable;

import cn.nju.game.fight.MagicalDamage;
import cn.nju.game.fight.MagicalDenfence;
import cn.nju.game.fight.PhysicalDamage;
import cn.nju.game.fight.PhysicalDefence;

/**
 * 装备
 */
public abstract class Equipment implements PhysicalDamage, PhysicalDefence, MagicalDamage, MagicalDenfence, Serializable {
	private static final long serialVersionUID = 5540851933991657184L;
	/**
	 * Default constructor
	 */
	public Equipment() {
	}

	/**
	 * 装备描述
	 */
	protected String description;

	/**
	 * 无力攻击
	 */
	protected int physicalDamage;

	/**
	 * 魔法攻击
	 */
	protected int magicDamage;

	/**
	 * 护甲值
	 */
	protected int armor;

	/**
	 * 魔法抗性
	 */
	protected int magicalResistance;

	/**
	 * 装备名称
	 */
	protected String name;

	/**
	 * 装备价格
	 */
	protected int price;

	/**
	 * 生命值提升
	 */
	protected int strengthHealth;

	/**
	 * 吸血
	 */
	protected float recoverHealth;

	/**
	 * 回能量
	 */
	protected float recoverEnergy;
	/**
	 * 计算物理伤害
	 */
	public int computePhysicalDamage() {
		return physicalDamage;
	}

	/**
	 * 计算魔法伤害
	 */
	public int computeMagicDamage() {
		return magicDamage;
	}

	/**
	 * 计算护甲值
	 */
	public int computeArmor() {
		return armor;
	}

	/**
	 * 计算魔法抗性
	 */
	public int computeMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * 计算装备价格
	 */
	public int computePrice() {
		return price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the physicalDamage
	 */
	public int getPhysicalDamage() {
		return physicalDamage;
	}

	/**
	 * @return the magicDamage
	 */
	public int getMagicDamage() {
		return magicDamage;
	}

	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * @return the magicalResistance
	 */
	public int getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the strengthHealth
	 */
	public int getStrengthHealth() {
		return strengthHealth;
	}

	/**
	 * @return the recoverHealth
	 */
	public float getRecoverHealth() {
		return recoverHealth;
	}

	/**
	 * @return the recoverEnergy
	 */
	public float getRecoverEnergy() {
		return recoverEnergy;
	}
	
}