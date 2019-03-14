/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.equip.Equipment;
import cn.nju.game.model.vo.DamageVO;

/**
 * 装备伤害计算器
 * @author frank
 *
 */
public class EquipmentDamageComputor implements DamageComputor {

	private Equipment equipment;
	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		int physicalDamage = equipment.computePhysicalDamage();
		int magicDamage = equipment.computeMagicDamage();
		DamageVO damage = new DamageVO();
		damage.setMagicalDamage(magicDamage);
		damage.setPhysicalDamage(physicalDamage);
		return damage;
	}

}
