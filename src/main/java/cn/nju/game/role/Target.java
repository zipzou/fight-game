package cn.nju.game.role;

/**
 * 生命体，可被攻击的目标
 */
public abstract class Target implements Damageable, Cloneable {

    /**
     * Default constructor
     */
    public Target() {
    }

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
     * 克隆方法，原型模式
     */
    @Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

    /**
     * 加血
     * @param healthVal 健康值
     */
    public void improveHealth(int healthVal) {
    	this.health += healthVal;
    }

	/**
     * 减少健康程度
     * @param healthVal 健康值
     */
    public void reduceHealth(int healthVal) {
    	this.health -= healthVal;
    }

    /**
     * 是否存活
     */
    public boolean isAlive() {
    	return 0 < health;
    }

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the physicalDamage
	 */
	public int getPhysicalDamage() {
		return physicalDamage;
	}

	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * @return the magicDamage
	 */
	public int getMagicDamage() {
		return magicDamage;
	}

	/**
	 * @return the magicalResistance
	 */
	public int getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @param health the health to set
	 */
	protected void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @param level the level to set
	 */
	protected void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param physicalDamage the physicalDamage to set
	 */
	protected void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	/**
	 * @param armor the armor to set
	 */
	protected void setArmor(int armor) {
		this.armor = armor;
	}

	/**
	 * @param magicDamage the magicDamage to set
	 */
	protected void setMagicDamage(int magicDamage) {
		this.magicDamage = magicDamage;
	}

	/**
	 * @param magicalResistance the magicalResistance to set
	 */
	protected void setMagicalResistance(int magicalResistance) {
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
	protected void setIcon(String icon) {
		this.icon = icon;
	}

}