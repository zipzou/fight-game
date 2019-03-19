/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.fight.Memento;

/**
 * 目标的备忘录接口
 * @author frank
 *
 */
public class TargetMemento implements Memento {
	/**
     * 目标健康程度
     */
    private int health;

    /**
     * 目标等级
     */
    private int level;

    /**
     * 物理攻击力
     */
    private int physicalDamage;

    /**
     * 护甲值
     */
    private int armor;

    /**
     * 魔法攻击力
     */
    private int magicDamage;

    /**
     * 魔法抗性
     */
    private int magicalResistance;
    
    /**
     * 经验值
     */
//    private int expirience;

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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
	 * @return the magicDamage
	 */
	public int getMagicDamage() {
		return magicDamage;
	}

	/**
	 * @param magicDamage the magicDamage to set
	 */
	public void setMagicDamage(int magicDamage) {
		this.magicDamage = magicDamage;
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

//	/**
//	 * @return the expirience
//	 */
//	public int getExpirience() {
//		return expirience;
//	}
//
//	/**
//	 * @param expirience the expirience to set
//	 */
//	public void setExpirience(int expirience) {
//		this.expirience = expirience;
//	}
}
