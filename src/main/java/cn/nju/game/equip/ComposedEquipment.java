package cn.nju.game.equip;

import java.util.*;

/**
 * 合成装备
 */
public class ComposedEquipment extends Equipment {

	/**
	 * Default constructor
	 */
	protected ComposedEquipment() {
		subEquipments = new HashSet<Equipment>();
	}

	/**
	 * 子装备
	 */
	protected Set<Equipment> subEquipments;

	/**
	 * 组合装备构建器
	 * 
	 * @author frank
	 *
	 */
	protected static class ConComposedEquipmentBuilder extends SingleEquipment.SingleEquipmentBuilder
			implements ComposedEquipmentBuilder {

		protected Set<Equipment> equipments;

		/**
		 * 
		 */
		protected ConComposedEquipmentBuilder() {
			super();
			equipments = new HashSet<Equipment>();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * cn.nju.game.equip.ComposedEquipmentBuilder#add(cn.nju.game.equip.Equipment)
		 */
		public ComposedEquipmentBuilder add(Equipment equipment) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Add sub equipment: " + equipment.name + "...");
			}
			equipments.add(equipment);
			return this;
		}

	}

	/**
	 * 获取组合装备构建器
	 * @return 组合装备构建器
	 */
	public static ComposedEquipmentBuilder builder() {
		return new ConComposedEquipmentBuilder();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Equipment#computePhysicalDamage()
	 */
	@Override
	public int computePhysicalDamage() {
		int damage = super.computePhysicalDamage();
		for (Equipment subEquipment : subEquipments) {
			damage += subEquipment.computePhysicalDamage();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Equipment#computeMagicDamage()
	 */
	@Override
	public int computeMagicDamage() {
		int damage = super.computeMagicDamage();
		for (Equipment subEquipment : subEquipments) {
			damage += subEquipment.computeMagicDamage();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Equipment#computeArmor()
	 */
	@Override
	public int computeArmor() {
		int damage = super.computeArmor();
		for (Equipment subEquipment : subEquipments) {
			damage += subEquipment.computeArmor();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Equipment#computeMagicalResistance()
	 */
	@Override
	public int computeMagicalResistance() {
		int damage = super.computeMagicalResistance();
		for (Equipment subEquipment : subEquipments) {
			damage += subEquipment.computeMagicalResistance();
		}
		return damage;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Equipment#computePrice()
	 */
	@Override
	public int computePrice() {
		int damage = super.computePrice();
		for (Equipment subEquipment : subEquipments) {
			damage += subEquipment.computePrice();
		}
		return damage;
	}
	
	
}