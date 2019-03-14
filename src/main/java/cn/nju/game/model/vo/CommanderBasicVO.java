/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * @author frank
 *
 */
public abstract class CommanderBasicVO extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -630759795110849827L;
	/**
     * 目标健康程度
     */
    private int health;

    /**
     * 目标名称
     */
    private String name;

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
     * 图像
     */
    private String icon;
    
    /**
     * 经验值
     */
    private int expirience;

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

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the expirience
	 */
	public int getExpirience() {
		return expirience;
	}

	/**
	 * @param expirience the expirience to set
	 */
	public void setExpirience(int expirience) {
		this.expirience = expirience;
	}

	/**
	 * 获取召唤师职业类型
	 * @return
	 */
	public abstract String getJob();
}
