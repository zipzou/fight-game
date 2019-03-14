/**
 * 
 */
package cn.nju.game.fight.test;

import org.junit.Before;
import org.junit.Test;

import cn.nju.game.equip.EquipmentBag;
import cn.nju.game.equip.EquipmentPool;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.role.Commander;
import cn.nju.game.role.CommanderPartner;
import cn.nju.game.role.MagicalMonster;
import cn.nju.game.role.MonsterPartner;
import cn.nju.game.role.PhysicalMonster;
import cn.nju.game.role.StageFightMediator;
import cn.nju.game.role.StagePartnerMediator;
import cn.nju.game.role.factory.WarriorFactory;
import cn.nju.game.weapon.DamageComputable;
import cn.nju.game.weapon.LeadingWeaponPart;
import cn.nju.game.weapon.TailWeaponPart;
import cn.nju.game.weapon.Weapon;

/**
 * @author frank
 *
 */
public class FightTest {

	EquipmentPool shop;
	
	DamageComputable weapon;

	private EquipmentBag equipmentBag;

	private PhysicalMonster physicalMonster;

	private MagicalMonster magicalMonster;

	private Commander commander;

	private StagePartnerMediator stageFightMediator;
	
	@Before
	public void init() {
		shop = EquipmentShop.sharedPool();
		weapon = new Weapon();
		weapon = new LeadingWeaponPart(weapon);
		weapon = new TailWeaponPart(weapon);
		equipmentBag = new EquipmentBag();
		equipmentBag.add(shop.getEquipment("黑色切割者"));
		equipmentBag.add(shop.getEquipment("燃烧宝石"));
		equipmentBag.add(shop.getEquipment("多兰之刃"));
		commander = new WarriorFactory().produceCommander("测试人员");
		magicalMonster = new MagicalMonster();
		physicalMonster = new PhysicalMonster();
		stageFightMediator = new StageFightMediator();
	}
	
	@Test
	public void testFight() {
		CommanderPartner commanderPartner = new CommanderPartner();
		commanderPartner.setTarget(commander);
		commanderPartner.setWeapon(weapon);
		commanderPartner.setEquipments(equipmentBag);
		MonsterPartner monsterPartner = new MonsterPartner();
		MonsterPartner monsterPartner2 = new MonsterPartner();
		monsterPartner.setTarget(magicalMonster);
		monsterPartner2.setTarget(physicalMonster);
		stageFightMediator.register(commanderPartner);
		stageFightMediator.register(monsterPartner);
		stageFightMediator.register(monsterPartner2);
		commanderPartner.setMediator(stageFightMediator);
		monsterPartner.setMediator(stageFightMediator);
		monsterPartner2.setMediator(stageFightMediator);
		
		commanderPartner.attack();
		
		monsterPartner.attack();
		
		monsterPartner2.attack();
		
	}
	
}
