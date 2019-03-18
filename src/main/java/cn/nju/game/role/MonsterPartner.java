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
import cn.nju.game.skill.Skill;
import cn.nju.game.weapon.DamageComputable;
import cn.nju.game.weapon.Weapon;

/**
 * 野怪竞技场参与者
 * @author frank
 *
 */
public class MonsterPartner extends StagePartner implements Attacked {
	private static final Logger LOG = Logger.getLogger(MonsterPartner.class);

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
		if (source instanceof PhysicalMonster || source instanceof MagicalMonster) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Monster doesnot attack monsters.");
			}
			return;
		}
		DamageComputor damageComputor = new DamageComputorManager(source, weapon, equipments, skill);
		DamageVO damage = damageComputor.compute();
		DefenceComputorManager defenceComputor = new DefenceComputorManager(getTarget(), new EquipmentBag());
		DefenceVO defence = defenceComputor.compute();

		DamageVO damageValue = new DamageVO();
		// 计算魔法伤害及物理伤害
		int physicalDamage = damage.getPhysicalDamage() - defence.getArmor();
		physicalDamage = 0 > physicalDamage ? 0 : physicalDamage;
		damageValue.setPhysicalDamage(physicalDamage);
		int magicalDamage = damage.getMagicalDamage() - defence.getMagicalResistance();
		magicalDamage = 0 > magicalDamage ? 0 : magicalDamage;
		damageValue.setMagicalDamage(magicalDamage);
		if (LOG.isInfoEnabled()) {
			LOG.info("Will injured " + damageValue.getPhysicalDamage() + " from physical damage, and " + damageValue.getMagicalDamage() + " from magical damage.");
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
			int healthVal = (int) (healthImprovedRate * (damageValue.getPhysicalDamage() + damageValue.getMagicalDamage()));
			
			source.improveHealth(healthVal);
		}
	}
}
