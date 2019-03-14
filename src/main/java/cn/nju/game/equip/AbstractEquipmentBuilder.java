/**
 * 
 */
package cn.nju.game.equip;

/**
 * 抽象装备构建器
 * @author frank
 *
 */
@Deprecated
public class AbstractEquipmentBuilder implements EquipmentBuilder {

	protected Equipment equipment;
	
	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#build()
	 */
	public Equipment build() {
		return equipment;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#description(java.lang.String)
	 */
	public EquipmentBuilder description(String desc) {
		equipment.description = desc;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#physicalDamage(int)
	 */
	public EquipmentBuilder physicalDamage(int damage) {
		equipment.physicalDamage = damage;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#magicDamage(int)
	 */
	public EquipmentBuilder magicalDamage(int damage) {
		equipment.magicDamage = damage;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#armor(int)
	 */
	public EquipmentBuilder armor(int armor) {
		equipment.armor = armor;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#magicalResistance(int)
	 */
	public EquipmentBuilder magicalResistance(int resistance) {
		equipment.magicalResistance = resistance;
		return this;
	}
//
//	/* (non-Javadoc)
//	 * @see cn.nju.game.equip.EquipmentBuilder#name(java.lang.String)
//	 */
//	public void name(String name) {
//		equipment.name = name;
//	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#price(int)
	 */
	public EquipmentBuilder price(int price) {
		equipment.price = price;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#strengthHealth(int)
	 */
	public EquipmentBuilder strengthHealth(int health) {
		equipment.strengthHealth = health;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#recoverHealth(float)
	 */
	public EquipmentBuilder recoverHealth(float rate) {
		equipment.recoverHealth = rate;
		return this;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.EquipmentBuilder#recoverEnergy(float)
	 */
	public EquipmentBuilder recoverEnergy(float rate) {
		equipment.recoverHealth = rate;
		return this;
	}

	public EquipmentBuilder name(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public EquipmentBuilder add(Equipment equipment) {
		// TODO Auto-generated method stub
		return null;
	}
}
