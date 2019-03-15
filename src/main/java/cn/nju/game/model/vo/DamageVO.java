/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * 伤害值VO
 * @author frank
 *
 */
public final class DamageVO {

	private int physicalDamage;
	
	private int magicalDamage;
	
	private int trueDamage;
	
	private float strengthRate;			//伤害增强比率

	/**
	 * @return the strengthRate
	 */
	public float getStrengthRate() {
		return strengthRate;
	}

	/**
	 * @param strengthRate the strengthRate to set
	 */
	public void setStrengthRate(float strengthRate) {
		this.strengthRate = strengthRate;
	}

	/**
	 * @return the physicalDamage
	 */
	public int getPhysicalDamage() {
		return physicalDamage;
	}

	/**
	 * @param physicalDamage the physicalDamage to set
	 */
	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	/**
	 * @return the magicalDamage
	 */
	public int getMagicalDamage() {
		return magicalDamage;
	}

	/**
	 * @return the trueDamage
	 */
	public int getTrueDamage() {
		return trueDamage;
	}

	/**
	 * @param trueDamage the trueDamage to set
	 */
	public void setTrueDamage(int trueDamage) {
		this.trueDamage = trueDamage;
	}

	/**
	 * @param magicalDamage the magicalDamage to set
	 */
	public void setMagicalDamage(int magicalDamage) {
		this.magicalDamage = magicalDamage;
	}
	
	public DamageVO plus(DamageVO damage) {
		DamageVO result = new DamageVO(); // 结果
		result.setPhysicalDamage(damage.getPhysicalDamage() + getPhysicalDamage());
		result.setMagicalDamage(magicalDamage + damage.getMagicalDamage());
		result.trueDamage += damage.getTrueDamage();
		result.strengthRate += damage.strengthRate;
		return result;
	}
	
	public DamageVO substract(DamageVO damage) {
		DamageVO result = new DamageVO(); // 结果
		result.setPhysicalDamage(0 > physicalDamage - damage.getPhysicalDamage() ? 0 : physicalDamage - damage.getPhysicalDamage());
		result.setMagicalDamage(0 > magicalDamage - damage.getMagicalDamage() ? 0 : magicalDamage - damage.getMagicalDamage());
		result.trueDamage -= damage.getTrueDamage();
		result.strengthRate -= damage.strengthRate;
		
		return result;
	}
}
