/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.equip.Equipment;
import cn.nju.game.model.vo.DefenceVO;

/**
 * 装备抗性计算
 * @author frank
 *
 */
public class EquipmentDenfenceComputor implements DefenceComputor {

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
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		DefenceVO defence = new DefenceVO();
		
		int armor = equipment.computeArmor();
		int magicalResistance = equipment.computeMagicalResistance();
		float recoverHealth = equipment.getRecoverHealth();
		float recoverEnergy = equipment.getRecoverEnergy();
		if (0 == ((int) recoverHealth)) {
			defence.setRecoverHealthRate(recoverHealth);
		} else {
			defence.setRecoveredHealth((int) recoverHealth);
		}
		if (0 == ((int) recoverEnergy)) {
			defence.setRecoverEnergyRate(recoverEnergy);
		} else {
			defence.setRecoverdEnergy((int) recoverEnergy);
		}
		
		defence.setArmor(armor);
		defence.setMagicalResistance(magicalResistance);
		return defence;
	}

}
