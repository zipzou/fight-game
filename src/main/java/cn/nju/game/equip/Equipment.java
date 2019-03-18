package cn.nju.game.equip;

import java.io.Serializable;

import cn.nju.game.fight.HealthStrength;
import cn.nju.game.fight.MagicalDamage;
import cn.nju.game.fight.MagicalDenfence;
import cn.nju.game.fight.PhysicalDamage;
import cn.nju.game.fight.PhysicalDefence;

/**
 * 装备
 */
public abstract class Equipment implements PhysicalDamage, PhysicalDefence, MagicalDamage, MagicalDenfence, Serializable, HealthStrength {
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
	protected int magicalDamage;

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
		return magicalDamage;
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
	 * @return the magicalDamage
	 */
	public int getMagicalDamage() {
		return magicalDamage;
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

	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param physicalDamage the physicalDamage to set
	 */
	protected void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	/**
	 * @param magicalDamage the magicalDamage to set
	 */
	protected void setMagicalDamage(int magicalDamage) {
		this.magicalDamage = magicalDamage;
	}

	/**
	 * @param armor the armor to set
	 */
	protected void setArmor(int armor) {
		this.armor = armor;
	}

	/**
	 * @param magicalResistance the magicalResistance to set
	 */
	protected void setMagicalResistance(int magicalResistance) {
		this.magicalResistance = magicalResistance;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 */
	protected void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @param strengthHealth the strengthHealth to set
	 */
	protected void setStrengthHealth(int strengthHealth) {
		this.strengthHealth = strengthHealth;
	}

	/**
	 * @param recoverHealth the recoverHealth to set
	 */
	protected void setRecoverHealth(float recoverHealth) {
		this.recoverHealth = recoverHealth;
	}

	/**
	 * @param recoverEnergy the recoverEnergy to set
	 */
	protected void setRecoverEnergy(float recoverEnergy) {
		this.recoverEnergy = recoverEnergy;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.HealthStrength#computeHealthImproved()
	 */
	public int computeHealthImproved() {
		return strengthHealth;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.HealthStrength#computeHealthRecoverRate()
	 */
	public float computeHealthRecoverRate() {
		return recoverHealth;
	}
	
}