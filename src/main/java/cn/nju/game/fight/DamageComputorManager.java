/**
 * 
 */
package cn.nju.game.fight;

import org.apache.log4j.Logger;

import cn.nju.game.model.vo.DamageVO;
import cn.nju.game.role.MagicianCommander;
import cn.nju.game.role.WarriorCommander;

/**
 * 伤害计算外观
 * @author frank
 *
 */
public class DamageComputorManager implements DamageComputor {

	private static final Logger LOG = Logger.getLogger(DamageComputorManager.class);
	
	private DamageComputor[] computors;
	/**
	 * 
	 */
	protected DamageComputorManager(CommanderDamageComputor fromCommander, CommanderDamageComputor fromWeapon, EquipmentDamageComputor fromEquipment) {
		super();
		computors = new DamageComputor[3];
		computors[0] = fromCommander;
		computors[1] = fromWeapon;
		computors[2] = fromEquipment;
		if (LOG.isInfoEnabled()) {
			LOG.info("Start computing damage...");
		}
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		int physicalDamage = 0, magicalDamage = 0;
		if (LOG.isInfoEnabled()) {
			LOG.info("Start computing commander damage...");
		}
		DamageVO commander = computors[0].compute();
		DamageVO weapon = computors[1].compute();
		DamageVO equipment = computors[2].compute();
		if (LOG.isInfoEnabled()) {
			LOG.info("Start computing equipment damage...");
		}
		CommanderDamageComputor commanderComputor = (CommanderDamageComputor) computors[0];
		physicalDamage += commander.getPhysicalDamage();
		magicalDamage += commander.getMagicalDamage();
		if (commanderComputor.getCommander() instanceof WarriorCommander) {
			physicalDamage += weapon.getPhysicalDamage();
			if (LOG.isInfoEnabled()) {
				LOG.info("Start computing weapon damage...");
			}
		} else if (commanderComputor.getCommander() instanceof MagicianCommander) {
			magicalDamage += weapon.getPhysicalDamage();
			if (LOG.isInfoEnabled()) {
				LOG.info("Start computing physical damage...");
			}
		}
		physicalDamage += equipment.getPhysicalDamage();
		magicalDamage += equipment.getMagicalDamage();
		
		DamageVO damage = new DamageVO();
		damage.setMagicalDamage(magicalDamage);
		damage.setPhysicalDamage(physicalDamage);
		return damage;
	}

}
