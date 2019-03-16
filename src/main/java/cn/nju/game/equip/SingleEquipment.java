/**
 * 
 */
package cn.nju.game.equip;

import org.apache.log4j.Logger;

/**
 * @author frank
 *
 */
public class SingleEquipment extends Equipment {
	private static final long serialVersionUID = 5915507650892671889L;
	protected static final Logger LOG = Logger.getLogger(SingleEquipment.class);
	
	/**
	 * 
	 */
	protected SingleEquipment() {
		super();
	}

	/**
	 * 获取装备构建器
	 * @return 装备构建器
	 */
	public static EquipmentBuilder builder() {
		if (LOG.isInfoEnabled()) {
			LOG.info("Start single equipment builder....");
		}
		return new SingleEquipmentBuilder();
	}
	
	
	
	/**
	 * 单一装备构建器
	 * @author frank
	 *
	 */
	protected static class SingleEquipmentBuilder implements EquipmentBuilder {

		protected Equipment equipment;

		protected static final Logger LOG = Logger.getLogger(SingleEquipmentBuilder.class);

		protected SingleEquipmentBuilder() {
			equipment = new SingleEquipment();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#build()
		 */
		public Equipment build() {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build equipment complete!");
			}
			return equipment;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#description(java.lang.String)
		 */
		public EquipmentBuilder description(String desc) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment description : " + desc + " ...");
			}
			equipment.description = desc;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#physicalDamage(int)
		 */
		public EquipmentBuilder physicalDamage(int damage) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment physical damage : " + damage + " ...");
			}
			equipment.physicalDamage = damage;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#magicDamage(int)
		 */
		public EquipmentBuilder magicalDamage(int damage) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment magical damage : " + damage + " ...");
			}
			equipment.magicalDamage = damage;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#armor(int)
		 */
		public EquipmentBuilder armor(int armor) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment armor : " + armor + " ...");
			}
			equipment.armor = armor;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#magicalResistance(int)
		 */
		public EquipmentBuilder magicalResistance(int resistance) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment magical resistance : " + resistance + " ...");
			}
			equipment.magicalResistance = resistance;
			return this;
		}
		//
		// /* (non-Javadoc)
		// * @see cn.nju.game.equip.EquipmentBuilder#name(java.lang.String)
		// */
		// public void name(String name) {
		// equipment.name = name;
		// }

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#price(int)
		 */
		public EquipmentBuilder price(int price) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment price : " + price + " ...");
			}
			equipment.price = price;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#strengthHealth(int)
		 */
		public EquipmentBuilder strengthHealth(int health) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment strength health : " + health + " ...");
			}
			equipment.strengthHealth = health;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#recoverHealth(float)
		 */
		public EquipmentBuilder recoverHealth(float rate) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment recover health : " + rate + " ...");
			}
			equipment.recoverHealth = rate;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see cn.nju.game.equip.EquipmentBuilder#recoverEnergy(float)
		 */
		public EquipmentBuilder recoverEnergy(float rate) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment recover energy health : " + rate + " ...");
			}
			equipment.recoverHealth = rate;
			return this;
		}

		/* (non-Javadoc)
		 * @see cn.nju.game.equip.EquipmentBuilder#name(java.lang.String)
		 */
		public EquipmentBuilder name(String name) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build Equipment name : " + name + " ...");
			}
			equipment.name = name;
			return this;
		}
		
		/* (non-Javadoc)
		 * @see cn.nju.game.equip.EquipmentBuilder#add(cn.nju.game.equip.Equipment)
		 */
		public EquipmentBuilder add(Equipment equipment) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Single equipment will not consist any sub equipments!");
			}
			return this;
		}
	}
}
