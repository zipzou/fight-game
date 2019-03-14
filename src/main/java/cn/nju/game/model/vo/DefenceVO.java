/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * 防御性指标
 * @author frank
 *
 */
public class DefenceVO {

	/**
	 * 护甲值
	 */
	private int armor;
	
	/**
	 * 魔法抗性
	 */
	private int magicalResistance;
	
	/**
	 * 回复的生命值
	 */
	private int recoveredHealth;
	
	/**
	 * 回复的能量值
	 */
	private int recoverdEnergy;
	
	/**
	 * 生命值回复绿
	 */
	private float recoverHealthRate;
	
	/**
	 * 能量值回复率
	 */
	private float recoverEnergyRate;

	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}

	/**
	 * @return the magicalResistance
	 */
	public int getMagicalResistance() {
		return magicalResistance;
	}

	/**
	 * @param magicalResistance the magicalResistance to set
	 */
	public void setMagicalResistance(int magicalResistance) {
		this.magicalResistance = magicalResistance;
	}

	/**
	 * @return the recoveredHealth
	 */
	public int getRecoveredHealth() {
		return recoveredHealth;
	}

	/**
	 * @param recoveredHealth the recoveredHealth to set
	 */
	public void setRecoveredHealth(int recoveredHealth) {
		this.recoveredHealth = recoveredHealth;
	}

	/**
	 * @return the recoverdEnergy
	 */
	public int getRecoverdEnergy() {
		return recoverdEnergy;
	}

	/**
	 * @param recoverdEnergy the recoverdEnergy to set
	 */
	public void setRecoverdEnergy(int recoverdEnergy) {
		this.recoverdEnergy = recoverdEnergy;
	}
	/**
	 * @return the recoverHealthRate
	 */
	public float getRecoverHealthRate() {
		return recoverHealthRate;
	}

	/**
	 * @param recoverHealthRate the recoverHealthRate to set
	 */
	public void setRecoverHealthRate(float recoverHealthRate) {
		this.recoverHealthRate = recoverHealthRate;
	}

	/**
	 * @return the recoverEnergyRate
	 */
	public float getRecoverEnergyRate() {
		return recoverEnergyRate;
	}

	/**
	 * @param recoverEnergyRate the recoverEnergyRate to set
	 */
	public void setRecoverEnergyRate(float recoverEnergyRate) {
		this.recoverEnergyRate = recoverEnergyRate;
	}
	
	public DefenceVO plus(DefenceVO defence) {
		DefenceVO result = new DefenceVO(); // 结果
		result.setArmor(defence.getArmor() + getArmor());
		result.setMagicalResistance(getMagicalResistance() + defence.getMagicalResistance());
		result.setRecoverdEnergy(recoverdEnergy + defence.getRecoverdEnergy());
		result.setRecoveredHealth(recoveredHealth + defence.getRecoveredHealth());
		result.setRecoverHealthRate(recoverHealthRate + defence.getRecoverHealthRate());
		result.setRecoverEnergyRate(recoverEnergyRate + defence.getRecoverEnergyRate());
		return result;
	}
}
