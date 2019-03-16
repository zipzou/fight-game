/**
 * 
 */
package cn.nju.game.skill;

import cn.nju.game.role.Target;

/**
 * 抽象技能
 * @author frank
 *
 */
public class BasedSkill implements Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7999359029141788446L;

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
    private int magicalDamage;

    /**
     * 魔法抗性
     */
    private int magicalResistance;
    
    /**
     * 技能描述
     */
    private String description;
    /**
	 * @param physicalDamage 造成的基础物理伤害
	 * @param armor 造成的基础护甲值
	 * @param magicDamage 造成的基础魔法伤害
	 * @param magicalResistance 造成的基础魔法抗性
	 * @param description 技能描述
	 * @param name 技能名称
	 */
	public BasedSkill(int physicalDamage, int armor, int magicDamage, int magicalResistance, String description,
			String name) {
		super();
		this.physicalDamage = physicalDamage;
		this.armor = armor;
		this.magicalDamage = magicDamage;
		this.magicalResistance = magicalResistance;
		this.description = description;
		this.name = name;
	}
	
	/**
	 * 构造基础技能
	 * @param description 技能描述
	 * @param name 技能名称
	 */
	public BasedSkill(String description, String name) {
		this.description = description;
		this.name = name;
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
	 * @param magicalDamage the magicalDamage to set
	 */
	protected void setMagicalDamage(int magicalDamage) {
		this.magicalDamage = magicalDamage;
	}
	/**
	 * @param magicalResistance the magicalResistance to set
	 */
	protected void setMagicalResistance(int magicalResistance) {
		this.magicalResistance = magicalResistance;
	}
	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}
	/**
     * 技能名称
     */
    private String name;
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
	public int getMagicalDamage() {
		return magicalDamage;
	}
	/**
	 * @return the magicalResistance
	 */
	public int getMagicalResistance() {
		return magicalResistance;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDamage#computeMagicDamage()
	 */
	public int computeMagicDamage() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDenfence#computeMagicalResistance()
	 */
	public int computeMagicalResistance() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDefence#computeArmor()
	 */
	public int computeArmor() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDamage#computePhysicalDamage()
	 */
	public int computePhysicalDamage() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#conjure(cn.nju.game.role.Target)
	 */
	public void conjure(Target target) {
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getLevel()
	 */
	public int getLevel() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#upgrade()
	 */
	public Skill upgrade() {
		int level = getLevel();
		if (4 > level) {
			return SkillLeveledPool.sharedPool().getSkill(name, level + 1);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getEnergyNeeded()
	 */
	public int getEnergyNeeded() {
		return 0;
	}
}
