/**
 * 
 */
package cn.nju.game.skill;

import java.util.Stack;

import org.apache.commons.lang3.NotImplementedException;

import cn.nju.game.role.Target;

/**
 * 技能组合
 * @author frank
 *
 */
public class SkillGroup implements ComposedSkill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4307400845030488002L;
	public Stack<Skill> skillStack;
	
	/**
	 * 
	 */
	public SkillGroup() {
		super();
		skillStack = new Stack<Skill>();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#conjure(cn.nju.game.role.Target)
	 */
	public void conjure(Target target) {
		for (Skill skill : skillStack) {
			skill.conjure(target);
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getLevel()
	 */
	public int getLevel() {
		throw new NotImplementedException("This method is not supported by grouped skill.");
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#upgrade()
	 */
	public Skill upgrade() {
		throw new NotImplementedException("This method is not supported by grouped skill.");
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDamage#computePhysicalDamage()
	 */
	public int computePhysicalDamage() {
		int damage = 0;
		for (Skill skill : skillStack) {
			damage += skill.computePhysicalDamage();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.PhysicalDefence#computeArmor()
	 */
	public int computeArmor() {
		int armor = 0;
		for (Skill skill : skillStack) {
			armor += skill.computeArmor();
		}
		return armor;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDamage#computeMagicDamage()
	 */
	public int computeMagicDamage() {
		int damage = 0;
		for (Skill skill : skillStack) {
			damage += skill.computeMagicalResistance();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.MagicalDenfence#computeMagicalResistance()
	 */
	public int computeMagicalResistance() {
		int resistance = 0;
		for (Skill skill : skillStack) {
			resistance += skill.computeMagicalResistance();
		}
		return resistance;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.ComposedSkill#add(cn.nju.game.skill.Skill)
	 */
	public void add(Skill skill) {
		skillStack.push(skill);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.ComposedSkill#cancelSkill()
	 */
	public Skill cancelSkill() {
		return skillStack.pop();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getEnergyNeeded()
	 */
	public int getEnergyNeeded() {
		int energy = 0;
		for (Skill skill : skillStack) {
			energy += skill.getEnergyNeeded();
		}
		return energy;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getName()
	 */
	public String getName() {
		String name = "";
		for (Skill skill : skillStack) {
			name += skill.getName() + "+";
		}
		return name.substring(0, name.length() - 1) + "组合技能";
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.skill.Skill#getDescription()
	 */
	public String getDescription() {
		String desc = "";
		for (Skill skill : skillStack) {
			desc += skill.getDescription() + ",";
		}
		return desc.substring(0, desc.length() - 1);
	}

}
