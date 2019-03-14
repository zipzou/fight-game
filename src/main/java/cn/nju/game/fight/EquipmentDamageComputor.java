/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.ComponentIterator;
import cn.nju.game.equip.Equipment;
import cn.nju.game.model.vo.DamageVO;

/**
 * 装备伤害计算器
 * @author frank
 *
 */
public class EquipmentDamageComputor implements DamageComputor {

	private Bag equipments;
	/**
	 * @param equipments
	 */
	protected EquipmentDamageComputor(Bag equipments) {
		super();
		this.equipments = equipments;
	}
	/**
	 * 
	 */
	protected EquipmentDamageComputor() {
		super();
	}
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
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DamageComputor#compute()
	 */
	public DamageVO compute() {
		DamageVO result = new DamageVO();
		ComponentIterator<Equipment> equipmentIt = equipments.iterator();
		while (equipmentIt.hasNext()) {
			Equipment equipment = equipmentIt.next();
			int physicalDamage = equipment.computePhysicalDamage();
			int magicDamage = equipment.computeMagicDamage();
			DamageVO damage = new DamageVO();
			damage.setMagicalDamage(magicDamage);
			damage.setPhysicalDamage(physicalDamage);
			result = result.plus(damage);
		}
		
		return result;
	}

}
