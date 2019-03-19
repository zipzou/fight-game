/**
 * 
 */
package cn.nju.game.role;

import org.apache.log4j.Logger;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.ComponentIterator;
import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.EquipmentBag;
import cn.nju.game.fight.DamageComputor;
import cn.nju.game.fight.DamageComputorManager;
import cn.nju.game.fight.DefenceComputorManager;
import cn.nju.game.model.vo.DamageVO;
import cn.nju.game.model.vo.DefenceVO;
import cn.nju.game.skill.ComposedSkill;
import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillLeveledPool;
import cn.nju.game.weapon.DamageComputable;
import cn.nju.game.weapon.Weapon;

/**
 * 召唤师参与者
 * @author frank
 *
 */
public class CommanderPartner extends StagePartner {
	private static final Logger LOG = Logger.getLogger(CommanderPartner.class);
	/**
	 * 
	 */
	public CommanderPartner() {
		super();
	}

	/**
	 * @param equipments 装备
	 * @param weapon 武器
	 * @param skill 技能
	 */
	public CommanderPartner(Bag equipments, DamageComputable weapon, Skill skill) {
		super();
		this.equipments = equipments;
		this.weapon = weapon;
		this.skill = skill;
	}

	/**
	 * 装备背包
	 */
	private Bag equipments;
	
	/**
	 * 武器
	 */
	private DamageComputable weapon;
	
	/**
	 * 组合技能
	 */
	private Skill skill;

	/**
	 * @return the equipments
	 */
	public Bag getEquipments() {
		return equipments;
	}

	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(Bag equipments) {
		this.equipments = equipments;
	}

	/**
	 * @return the weapon
	 */
	public DamageComputable getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(DamageComputable weapon) {
		this.weapon = weapon;
	}

	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(ComposedSkill skill) {
		this.skill = skill;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacked#attacked(cn.nju.game.role.Target, cn.nju.game.weapon.DamageComputable, cn.nju.game.equip.Bag, cn.nju.game.skill.Skill)
	 */
	public void attacked(Target source, DamageComputable weapon, Bag equipments, Skill skill) {
		// 计算伤害
		if (null == weapon) {
			weapon = new Weapon();
		}
		if (null == equipments) {
			equipments = new EquipmentBag();
		}
		if (null == skill) {
			skill = SkillLeveledPool.sharedPool().getBased();
		}
		DamageComputor damageComputor = new DamageComputorManager(source, weapon, equipments, skill);
		DamageVO damage = damageComputor.compute();
		DefenceComputorManager defenceComputor = new DefenceComputorManager(getTarget(), this.equipments);
		DefenceVO defence = defenceComputor.compute();
		
		DamageVO damageValue = new DamageVO();
		// 计算魔法伤害及物理伤害
		int physicalDamage = damage.getPhysicalDamage() - defence.getArmor();
		physicalDamage = 0 > physicalDamage ? 0 : physicalDamage;
		int magicalDamage = damage.getMagicalDamage() - defence.getMagicalResistance();
		magicalDamage = 0 > magicalDamage ? 0 : magicalDamage;
		damageValue.setPhysicalDamage(physicalDamage);
		damageValue.setMagicalDamage(magicalDamage);
		if (LOG.isInfoEnabled()) {
			LOG.info("Will injured " + damageValue.getPhysicalDamage() + " from physical damage, and " + damageValue.getMagicalDamage() + " from magical damage.");
		}
		// 计算回复效果
		int energyToRecover = 0;
		if (this.getTarget() instanceof Commander) {
			energyToRecover = (int) (defence.getRecoverdEnergy() + defence.getRecoverEnergyRate() * ((Commander) getTarget()).getEnergy());
			if (LOG.isInfoEnabled()) {
				LOG.info("Will recover " + energyToRecover + "energy...");
			}
			((Commander) getTarget()).improveEnergy(energyToRecover);
			if (LOG.isInfoEnabled()) {
				LOG.info("Commander's energy is :" + ((Commander) getTarget()).getEnergy());
			}
		}
		
		getTarget().reduceHealth(damageValue.getPhysicalDamage() + damageValue.getMagicalDamage());
		if (LOG.isInfoEnabled()) {
			LOG.info(getTarget().getName() + "'s health is :" + getTarget().getHealth());
		}
		
		// 计算回复效果
		if (source instanceof Commander) {
			float healthImprovedRate = 0;

			ComponentIterator<Equipment> equipIt = equipments.iterator();
			while (equipIt.hasNext()) {
				Equipment equipment = equipIt.next();
				healthImprovedRate += equipment.computeHealthRecoverRate();
			}

			// 根据伤害计算吸血
			int healthVal = (int) (healthImprovedRate
					* (damageValue.getPhysicalDamage() + damageValue.getMagicalDamage()));
			source.improveHealth(healthVal);
		}
	}
//
//	/* (non-Javadoc)
//	 * @see cn.nju.game.role.StagePartner#attack(cn.nju.game.skill.ComposedSkill)
//	 */
//	@Override
//	public void attack(ComposedSkill skills) {
//		super.attack(skills);
//	}
}
