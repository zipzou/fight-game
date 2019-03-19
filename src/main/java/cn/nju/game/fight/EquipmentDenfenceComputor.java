/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.ComponentIterator;
import cn.nju.game.equip.Equipment;
import cn.nju.game.model.vo.DefenceVO;

/**
 * 装备抗性计算
 * @author frank
 *
 */
public class EquipmentDenfenceComputor implements DefenceComputor {

	private Bag equipments;
	/**
	 * 
	 */
	protected EquipmentDenfenceComputor() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param equipments
	 */
	protected EquipmentDenfenceComputor(Bag equipments) {
		super();
		this.equipments = equipments;
	}
	/**
	 * @return the equipment
	 */
	public Bag getEquipments() {
		return equipments;
	}
	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipments(Bag equipments) {
		this.equipments = equipments;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		
		DefenceVO result = new DefenceVO();
		
		ComponentIterator<Equipment> equipIt = equipments.iterator();
		while (equipIt.hasNext()) {
			DefenceVO defence = new DefenceVO();
			Equipment equipment = equipIt.next();
			int armor = equipment.computeArmor();
			int healthStrength = equipment.computeHealthImproved();
			float healthRate = equipment.computeHealthRecoverRate();
			int magicalResistance = equipment.computeMagicalResistance();
//			equipment.compute
//			float recoverHealth = equipment.getRecoverHealth();
			float recoverEnergy = equipment.getRecoverEnergy();
			if (0 == ((int) healthStrength)) {
				defence.setRecoverHealthRate(healthStrength);
			} else {
//				defence.setRecoveredHealth((int) healthStrength);
			}
			defence.setRecoverEnergyRate(defence.getRecoverEnergyRate() + healthRate);
			if (0 == ((int) recoverEnergy)) {
				defence.setRecoverEnergyRate(recoverEnergy);
			} else {
				defence.setRecoverdEnergy((int) recoverEnergy);
			}
			
			defence.setArmor(armor);
			defence.setMagicalResistance(magicalResistance);
			result = result.plus(defence);
		}
		
		return result;
	}

}
