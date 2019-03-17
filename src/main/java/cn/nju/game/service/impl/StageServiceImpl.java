/**
 * 
 */
package cn.nju.game.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.EquipmentBag;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.role.Commander;
import cn.nju.game.role.CommanderAttackProxy;
import cn.nju.game.role.CommanderPartner;
import cn.nju.game.role.MagicalMonster;
import cn.nju.game.role.MonsterPartner;
import cn.nju.game.role.PhysicalMonster;
import cn.nju.game.role.StageFightMediator;
import cn.nju.game.role.StagePartnerMediator;
import cn.nju.game.role.Target;
import cn.nju.game.service.OnlineCommander;
import cn.nju.game.service.SkillService;
import cn.nju.game.service.StageService;
import cn.nju.game.skill.Skill;
import cn.nju.game.skill.SkillLeveledPool;
import cn.nju.game.weapon.Weapon;

/**
 * 竞技场服务
 * @author frank
 *
 */
public class StageServiceImpl implements StageService {

	private List<CommanderBasicVO> commandersInfo;
	private Map<String, List<String>> equipmentsForCommander;
	private Map<String, List<SkillVO>> skillsForCommander;
	private StagePartnerMediator fightMediator;
	private Map<String, CommanderPartner> attackers;
	
	public StageServiceImpl() {
		super();
		commandersInfo = new ArrayList<CommanderBasicVO>();
		equipmentsForCommander = new HashMap<String, List<String>>();
		skillsForCommander = new HashMap<String, List<SkillVO>>();
		fightMediator = new StageFightMediator();
		attackers = new HashMap<String, CommanderPartner>();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#registerPartner(java.lang.String, java.util.List, java.util.List)
	 */
	public void registerPartner(String commanderName, List<String> equipments, List<SkillVO> skills) {
		CommanderBasicVO commanderInfo = OnlineCommander.sharedCommanders().get(commanderName).getBasicVO();
		commandersInfo.add(commanderInfo);
		equipmentsForCommander.put(commanderName, equipments);
		skillsForCommander.put(commanderName, skills);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getEquipments(int)
	 */
	public List<String> getEquipments(int i) {
		return equipmentsForCommander.get(commandersInfo.get(i).getName().toString());
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getSkills(int)
	 */
	public List<SkillVO> getSkills(int i) {
		return skillsForCommander.get(commandersInfo.get(i).getName().toString());
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getCommanderInfo(int)
	 */
	public CommanderBasicVO getCommanderInfo(int i) {
		return commandersInfo.get(i);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#ready()
	 */
	public void ready() {
		for (CommanderBasicVO commanderBasicVO : commandersInfo) {
			Bag equipBag = new EquipmentBag();
			List<String> equipNames = equipmentsForCommander.get(commanderBasicVO.getName());
			for (String equipName : equipNames) {
				equipBag.add(EquipmentShop.sharedPool().getEquipment(equipName));
			}
			Commander commander = OnlineCommander.sharedCommanders().get(commanderBasicVO.getName());
			Weapon weapon = commander.getWeapon();
			List<Skill> skills = new ArrayList<Skill>();
			for (SkillVO skillItem : skillsForCommander.get(commanderBasicVO.getName())) {
				Skill skill = SkillLeveledPool.sharedPool().getSkill(skillItem.getName(), skillItem.getLevel());
				skills.add(skill);
			}
			CommanderPartner commanderPartner = new CommanderPartner(equipBag, weapon, null);
			try {
				commanderPartner.setTarget(commander.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			} // 使用原型模式
			// 创建代理
			CommanderAttackProxy attackerProxy = new CommanderAttackProxy(equipBag, weapon, null);
			attackerProxy.setAttacker(commanderPartner);
			attackers.put(commanderBasicVO.getName(), attackerProxy);
			attackerProxy.setTarget(commanderPartner.getTarget());
//			attackerProxy.setMediator(fightMediator);
			fightMediator.register(attackerProxy.getAttacker()); // 需要注册被代理的对象，否则会产生攻击错误
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#registerPyhsicalPartner(java.lang.String, int)
	 */
	public void registerPyhsicalPartner(int count) {
		for(int i = 0; i < count; i++) {
			MonsterPartner monsterPartner = new MonsterPartner();
			monsterPartner.setTarget(new PhysicalMonster());
			monsterPartner.setMediator(fightMediator);
			fightMediator.register(monsterPartner);
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#registerMagicalPartner(java.lang.String, int)
	 */
	public void registerMagicalPartner(int count) {
		for(int i = 0; i < count; i++) {
			MonsterPartner monsterPartner = new MonsterPartner();
			monsterPartner.setTarget(new MagicalMonster());
			monsterPartner.setMediator(fightMediator);
			fightMediator.register(monsterPartner);
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getCurrentHealth(java.lang.String)
	 */
	public int getCurrentHealth(String name) {
		return attackers.get(name).getTarget().getHealth();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#registerPartnerObserver(java.util.Observer)
	 */
	public void registerPartnerObserver(Observer observer) {
		for (Entry<String, CommanderPartner> attackerKey : attackers.entrySet()) {
			attackerKey.getValue().getTarget().addObserver(observer);
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#isFirstPartner(java.util.Observable)
	 */
	public boolean isFirstPartner(Observable o) {
		String firName = commandersInfo.get(0).getName();
		return attackers.get(firName).getTarget() == o;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#isSecondPartner(java.util.Observable)
	 */
	public boolean isSecondPartner(Observable o) {
		String firName = commandersInfo.get(1).getName();
		return attackers.get(firName).getTarget() == o;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#isMonster(java.util.Observable)
	 */
	public boolean isMonster(Observable o) {
		return !isFirstPartner(o) && !isSecondPartner(o);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getHealth(java.util.Observable)
	 */
	public int getHealth(Observable o) {
		return ((Target) o).getHealth();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getEnergy(java.util.Observable)
	 */
	public int getEnergy(Observable o) {
		if (isFirstPartner(o) || isSecondPartner(o)) {
			return ((Commander) o).getEnergy();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#attack(int, cn.nju.game.service.SkillService)
	 */
	public void attack(int i, SkillService skills) {
		CommanderPartner partner = attackers.get(commandersInfo.get(i - 1).getName());
		partner.setSkill(skills.composedSkills()); // TODO: Debug it
		partner.attack();
	}
}
