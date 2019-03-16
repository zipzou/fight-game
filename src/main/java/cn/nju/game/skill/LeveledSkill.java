/**
 * 
 */
package cn.nju.game.skill;

import cn.nju.game.role.Target;

/**
 * 等级升级
 * @author frank
 *
 */
public class LeveledSkill implements Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7061104556409917201L;

	private Skill baseSkill;												// 基础等级
	
	/**
     * 物理攻击力
     */
    private int physicalDamageImproved;

    /**
     * 护甲值
     */
    private int armorImproved;

    /**
     * 魔法攻击力
     */
    private int magicalDamageImproved;

    /**
     * 魔法抗性
     */
    private int magicalResistanceImproved;
    
    /**
     * 能量值
     */
    private int energy;
	
	/**
	 * @param baseSkill 基础技能
	 * @param physicalDamageImproved 物理攻击提升值
	 * @param armorImproved 护甲提升值
	 * @param magicDamageImproved 魔法伤害提升值
	 * @param magicalResistanceImproved 魔法抗性提升值
	 * @param energy 所需能量值
	 * @param level 技能等级
	 */
	public LeveledSkill(Skill baseSkill, int physicalDamageImproved, int armorImproved, int magicDamageImproved,
			int magicalResistanceImproved, int energy, int level) {
		super();
		this.baseSkill = baseSkill;
		this.physicalDamageImproved = physicalDamageImproved;
		this.armorImproved = armorImproved;
		this.magicalDamageImproved = magicDamageImproved;
		this.magicalResistanceImproved = magicalResistanceImproved;
		this.energy = energy;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#conjure(cn.nju.game.role.Target)
	 */
	public void conjure(Target target) {
		baseSkill.conjure(target);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDefence#computeArmor()
	 */
	public int computeArmor() {
		return armorImproved + baseSkill.computeArmor();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDamage#computeMagicDamage()
	 */
	public int computeMagicDamage() {
		return magicalDamageImproved + baseSkill.computeMagicDamage();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDenfence#computeMagicalResistance()
	 */
	public int computeMagicalResistance() {
		return magicalResistanceImproved + baseSkill.computeMagicalResistance();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDamage#computePhysicalDamage()
	 */
	public int computePhysicalDamage() {
		return physicalDamageImproved + baseSkill.computePhysicalDamage();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getLevel()
	 */
	public int getLevel() {
		return 1 + baseSkill.getLevel();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#upgrade()
	 */
	public Skill upgrade() {
		int level = getLevel();
		if (4 > level) {
			return SkillLeveledPool.sharedPool().getSkill(baseSkill.getName(), level + 1);
		}
		return this;
	}

	/**
	 * @param baseSkill the baseSkill to set
	 */
	protected void setBaseSkill(BasedSkill baseSkill) {
		this.baseSkill = baseSkill;
	}

	/**
	 * @param physicalDamageImproved the physicalDamageImproved to set
	 */
	protected void setPhysicalDamageImproved(int physicalDamageImproved) {
		this.physicalDamageImproved = physicalDamageImproved;
	}

	/**
	 * @param armorImproved the armorImproved to set
	 */
	protected void setArmorImproved(int armorImproved) {
		this.armorImproved = armorImproved;
	}
	/**
	 * @param magicalResistanceImproved the magicalResistanceImproved to set
	 */
	protected void setMagicalResistanceImproved(int magicalResistanceImproved) {
		this.magicalResistanceImproved = magicalResistanceImproved;
	}
	/**
	 * @return the baseSkill
	 */
	public Skill getBaseSkill() {
		return baseSkill;
	}

	/**
	 * @return the physicalDamageImproved
	 */
	public int getPhysicalDamageImproved() {
		return physicalDamageImproved;
	}

	/**
	 * @return the armorImproved
	 */
	public int getArmorImproved() {
		return armorImproved;
	}

	/**
	 * @return the magicalResistanceImproved
	 */
	public int getMagicalResistanceImproved() {
		return magicalResistanceImproved;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getEnergyNeeded()
	 */
	public int getEnergyNeeded() {
		return energy;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getName()
	 */
	public String getName() {
		return baseSkill.getName();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getDescription()
	 */
	public String getDescription() {
		return baseSkill.getDescription();
	}
	/**
	 * @return the magicalDamageImproved
	 */
	public int getMagicalDamageImproved() {
		return magicalDamageImproved;
	}

	/**
	 * @param magicalDamageImproved the magicalDamageImproved to set
	 */
	protected void setMagicalDamageImproved(int magicalDamageImproved) {
		this.magicalDamageImproved = magicalDamageImproved;
	}
}
