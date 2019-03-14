package cn.nju.game.equip;

/**
 * 装备
 */
public abstract class Equipment {

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
}