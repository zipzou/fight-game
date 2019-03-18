/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.equip.Bag;
import cn.nju.game.skill.ComposedSkill;
import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillUnsendRunner;
import cn.nju.game.weapon.DamageComputable;

/**
 * 
 * @author frank
 *
 */
public class CommanderAttackProxy extends CommanderPartner {

	private CommanderPartner attacker;
	private SkillUnsendRunner runner;

	public CommanderAttackProxy() {
		super();
	}

	public CommanderAttackProxy(Bag equipments, DamageComputable weapon, Skill skill) {
		super(equipments, weapon, skill);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#getEquipments()
	 */
	@Override
	public Bag getEquipments() {
		return attacker.getEquipments();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#setEquipments(cn.nju.game.equip.Bag)
	 */
	@Override
	public void setEquipments(Bag equipments) {
		super.setEquipments(equipments);
		attacker.setEquipments(equipments);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#getWeapon()
	 */
	@Override
	public DamageComputable getWeapon() {
		return attacker.getWeapon();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#setWeapon(cn.nju.game.weapon.DamageComputable)
	 */
	@Override
	public void setWeapon(DamageComputable weapon) {
		super.setWeapon(weapon);
		attacker.setWeapon(weapon);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#getSkill()
	 */
	@Override
	public Skill getSkill() {
		return attacker.getSkill();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#setSkill(cn.nju.game.skill.ComposedSkill)
	 */
	@Override
	public void setSkill(ComposedSkill skill) {
		super.setSkill(skill);
		attacker.setSkill(skill);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.CommanderPartner#attacked(cn.nju.game.role.Target, cn.nju.game.weapon.DamageComputable, cn.nju.game.equip.Bag, cn.nju.game.skill.Skill)
	 */
	@Override
	public void attacked(Target source, DamageComputable weapon, Bag equipments, Skill skill) {
		super.attacked(source, weapon, equipments, skill);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartner#getTarget()
	 */
	@Override
	public Target getTarget() {
		return attacker.getTarget();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartner#setTarget(cn.nju.game.role.Target)
	 */
	@Override
	public void setTarget(Target target) {
		super.setTarget(target);
		attacker.setTarget(target);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartner#getMediator()
	 */
	@Override
	public StagePartnerMediator getMediator() {
		return attacker.getMediator();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartner#setMediator(cn.nju.game.role.StagePartnerMediator)
	 */
	@Override
	public void setMediator(StagePartnerMediator mediator) {
		super.setMediator(mediator);
		attacker.setMediator(mediator);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartner#attack()
	 */
	@Override
	public void attack() {
		Target target = attacker.getTarget();
		if (!target.isAlive()) {
			return; // 已经阵亡的目标
		}
		if (target instanceof Commander) {
			Commander commander = (Commander) target;
			int energy = commander.getEnergy();
			Skill skills = attacker.getSkill();
			if (null == skills) {
				attacker.attack();
			} else {
				int energyNeeded = attacker.getSkill().getEnergyNeeded();
				if (energy > energyNeeded) {
					attacker.attack();
				} else {
					if (null != runner) {
						runner.run();
					}
				}
			}
		}
	}

	/**
	 * @return the attacker
	 */
	public CommanderPartner getAttacker() {
		return attacker;
	}

	/**
	 * @param attacker the attacker to set
	 */
	public void setAttacker(CommanderPartner attacker) {
		this.attacker = attacker;
	}

	/**
	 * @return the runner
	 */
	public SkillUnsendRunner getRunner() {
		return runner;
	}

	/**
	 * @param runner the runner to set
	 */
	public void setRunner(SkillUnsendRunner runner) {
		this.runner = runner;
	}
	
}
