/**
 * 
 */
package cn.nju.game.model.vo;

import cn.nju.game.equip.Equipment;

/**
 * 装备信息的value object
 * @author frank
 *
 */
public class EquipmentVO {

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
	
	public EquipmentVO(Equipment equipment) {
		description = equipment.getDescription();
		physicalDamage = equipment.computePhysicalDamage();
		magicalResistance = equipment.computeMagicalResistance();
		magicalDamage = equipment.computeMagicDamage();
		armor = equipment.computeArmor();
		name = equipment.getName();
		strengthHealth = equipment.getStrengthHealth();
		recoverEnergy = equipment.getRecoverEnergy();
		recoverHealth = equipment.getRecoverHealth();
		price = equipment.computePrice();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the physicalDamage
	 */
	public int getPhysicalDamage() {
		return physicalDamage;
	}

	/**
	 * @param physicalDamage the physicalDamage to set
	 */
	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	/**
	 * @return the magicalDamage
	 */
	public int getMagicalDamage() {
		return magicalDamage;
	}

	/**
	 * @param magicalDamage the magicalDamage to set
	 */
	public void setMagicalDamage(int magicalDamage) {
		this.magicalDamage = magicalDamage;
	}

	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}

	/**
	 * @return the magicalResistance
	 */
	public int getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @param magicalResistance the magicalResistance to set
	 */
	public void setMagicalResistance(int magicalResistance) {
		this.magicalResistance = magicalResistance;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the strengthHealth
	 */
	public int getStrengthHealth() {
		return strengthHealth;
	}

	/**
	 * @param strengthHealth the strengthHealth to set
	 */
	public void setStrengthHealth(int strengthHealth) {
		this.strengthHealth = strengthHealth;
	}

	/**
	 * @return the recoverHealth
	 */
	public float getRecoverHealth() {
		return recoverHealth;
	}

	/**
	 * @param recoverHealth the recoverHealth to set
	 */
	public void setRecoverHealth(float recoverHealth) {
		this.recoverHealth = recoverHealth;
	}

	/**
	 * @return the recoverEnergy
	 */
	public float getRecoverEnergy() {
		return recoverEnergy;
	}

	/**
	 * @param recoverEnergy the recoverEnergy to set
	 */
	public void setRecoverEnergy(float recoverEnergy) {
		this.recoverEnergy = recoverEnergy;
	}
}
