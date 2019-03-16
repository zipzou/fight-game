/**
 * 
 */
package cn.nju.game.model.vo;

import cn.nju.game.skill.BasedSkill;
import cn.nju.game.skill.LeveledSkill;
import cn.nju.game.skill.Skill;

/**
 * 技能的ValueObject
 * @author frank
 *
 */
public class SkillVO {
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
    
    private int level;
    /**
     * 技能名
     */
    private String name;
    public SkillVO(Skill skill) {
    	this.physicalDamage = skill.computePhysicalDamage();
    	this.magicalDamage = skill.computeMagicDamage();
    	this.armor = skill.computeArmor();
    	if (skill instanceof LeveledSkill) {
        	this.name = ((LeveledSkill) skill).getBaseSkill().getName();
        	this.description =  ((LeveledSkill) skill).getBaseSkill().getDescription();
    	} else {
    		this.name = ((BasedSkill) skill).getName();
    		this.description =  ((BasedSkill) skill).getName();
    	}
    	this.level = ((LeveledSkill) skill).getLevel();
    	this.magicalResistance = skill.computeMagicalResistance();
    	
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
}
