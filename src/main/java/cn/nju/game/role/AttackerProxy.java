/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.equip.Bag;
import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillUnsendRunner;
import cn.nju.game.weapon.DamageComputable;

/**
 * 攻击者代理，用来对目标发送攻击指令
 * @author frank
 *
 */
public class AttackerProxy extends StagePartner {

	private Attacker attacker;
	
	private SkillUnsendRunner runner;
	
	/**
	 * 
	 */
	public AttackerProxy() {
		super();
	}
	/**
	 * @param attacker
	 */
	public AttackerProxy(Attacker attacker) {
		super();
		this.attacker = attacker;
	}

	/**
	 * @return the attacker
	 */
	public Attacker getAttacker() {
		return attacker;
	}
	/**
	 * @param attacker the attacker to set
	 */
	public void setAttacker(Attacker attacker) {
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
	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacker#attack()
	 */
	public void attack() {
		if (attacker instanceof CommanderPartner) {
			// 检查是否满足条件
			CommanderPartner commanderPartner = ((CommanderPartner) attacker);
			Target target = commanderPartner.getTarget();
			if (target instanceof Commander) {
				Commander commander = (Commander) target;
				int energy = commander.getEnergy();
				int energyNeeded = commanderPartner.getSkill().getEnergyNeeded();
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
	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacked#attacked(cn.nju.game.role.Target, cn.nju.game.weapon.DamageComputable, cn.nju.game.equip.Bag, cn.nju.game.skill.Skill)
	 */
	public void attacked(Target source, DamageComputable weapon, Bag equipments, Skill skill) {
		
	}
}
