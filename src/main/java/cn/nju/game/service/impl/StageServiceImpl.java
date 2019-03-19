/**
 * 
 */
package cn.nju.game.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.EquipmentBag;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.role.Commander;
import cn.nju.game.role.CommanderAttackProxy;
import cn.nju.game.role.CommanderPartner;
import cn.nju.game.role.JuniorExperienceManager;
import cn.nju.game.role.LevelManager;
import cn.nju.game.role.MagicalMonster;
import cn.nju.game.role.MonsterPartner;
import cn.nju.game.role.PhysicalMonster;
import cn.nju.game.role.PrimaryExperienceManager;
import cn.nju.game.role.SeniorExperienceManager;
import cn.nju.game.role.StageFightMediator;
import cn.nju.game.role.StagePartner;
import cn.nju.game.role.StagePartnerMediator;
import cn.nju.game.role.Target;
import cn.nju.game.role.TargetMementoManager;
import cn.nju.game.service.ExprienceCollector;
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
public class StageServiceImpl implements StageService, ExprienceCollector {

	private List<CommanderBasicVO> commandersInfo;
	private List<LevelManager> commanderLevelManagers;
	private Map<String, List<String>> equipmentsForCommander;
	private Map<String, List<SkillVO>> skillsForCommander;
	private StagePartnerMediator fightMediator;
	private Map<String, CommanderPartner> attackers;
	private Map<String, Set<Target>> killedHistory;
	private Map<String, TargetMementoManager> targetMementoManager;
	
	public StageServiceImpl() {
		super();
		commandersInfo = new ArrayList<CommanderBasicVO>();
		equipmentsForCommander = new HashMap<String, List<String>>();
		skillsForCommander = new HashMap<String, List<SkillVO>>();
		fightMediator = new StageFightMediator();
		attackers = new HashMap<String, CommanderPartner>();
		killedHistory = new HashMap<String, Set<Target>>();
		commanderLevelManagers = new ArrayList<LevelManager>();
		targetMementoManager = new HashMap<String, TargetMementoManager>();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#registerPartner(java.lang.String, java.util.List, java.util.List)
	 */
	public void registerPartner(String commanderName, List<String> equipments, List<SkillVO> skills) {
		Commander commander = OnlineCommander.sharedCommanders().get(commanderName);
		CommanderBasicVO commanderInfo = commander.getBasicVO();
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
				commander = commander.clone();// 使用原型模式
				
				// 需要重新根据装备计算生命值
				int sum = 0;
				for (String equipname : equipNames) {
					sum += EquipmentShop.sharedPool().getEquipment(equipname).computeHealthImproved();
				}
				commander.improveHealth(sum);
				commanderBasicVO.setHealth(commander.getHealth());
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
			// 创建备忘录管理者，以适应角色复活
			if (!targetMementoManager.containsKey(commander.getName())) {
				targetMementoManager.put(commander.getName(), new TargetMementoManager());
			}
			targetMementoManager.get(commander.getName()).setMemento(commander.createMemento());
			LevelManager levelManager = new PrimaryExperienceManager(null);
			((PrimaryExperienceManager) levelManager).setCommander(commander);
			levelManager = new JuniorExperienceManager(levelManager);
			((JuniorExperienceManager) levelManager).setCommander(commander);
			levelManager = new SeniorExperienceManager(levelManager);
			((SeniorExperienceManager) levelManager).setCommander(commander);
			commanderLevelManagers.add(levelManager);
			commanderPartner.setTarget(commander);
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
		Iterator<StagePartner> it = fightMediator.getAllPartners().iterator();
		while (it.hasNext()) {
			it.next().getTarget().addObserver(observer);
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
		if (-1 == i) {
			// 野怪攻击
			Iterable<StagePartner> allPartners = fightMediator.getAllPartners();
			for (StagePartner stagePartner : allPartners) {
				if (stagePartner instanceof MonsterPartner) {
					stagePartner.attack();
				}
			}
			return;
		}
		CommanderPartner partner = attackers.get(commandersInfo.get(i - 1).getName());
		partner.setSkill(skills.composedSkills()); 
		partner.attack();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#clearDied()
	 */
	public void clearDied() {
		Iterable<StagePartner> allPartners = fightMediator.getAllPartners();
		Iterator<StagePartner> partnerIt = allPartners.iterator();
		while (partnerIt.hasNext()) {
			StagePartner stagePartner = partnerIt.next();
			if (stagePartner instanceof MonsterPartner && !stagePartner.getTarget().isAlive()) {
//				partnerIt.remove();
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#getWinner()
	 */
	public CommanderBasicVO getWinner() {
		Iterator<StagePartner> partnerIt = fightMediator.getAllPartners().iterator();
		int alive = 0;
		Target winner = null;
		while (partnerIt.hasNext()) {
			Target attackerTarget = partnerIt.next().getTarget();
			if (attackerTarget.isAlive()) {
				if (attackerTarget instanceof Commander) {
					alive++;
					winner = attackerTarget;
				}
			}
		}
		if (1 >= alive) {
			// 获胜则计算经验
			for (int i = 0; i < commandersInfo.size(); i++) {
				if (commandersInfo.get(i).getName().equals(winner.getName())) {
					commanderLevelManagers.get(i).handleExprience(this);
					break;
				}
			}
			return ((Commander) winner).getBasicVO();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.ExprienceCollector#getExprience(java.lang.String)
	 */
	public int getExprience(String name) {
		Set<Target> killedTarget = killedHistory.get(name);
		int sum = 0;
		for (Target target : killedTarget) {
			sum += target.getKilledExp();
		}
		return sum;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.ExprienceCollector#getLevel(java.lang.String)
	 */
	public int getLevel(String name) {
		return attackers.get(name).getTarget().getLevel();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#collectExprience(java.lang.String, cn.nju.game.role.Target)
	 */
	public void collectExprience(String name, Target partner) {
		if (!killedHistory.containsKey(name)) {
			killedHistory.put(name, new HashSet<Target>());
		}
		killedHistory.get(name).add(partner);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.service.StageService#reviveAllCommander()
	 */
	public void reviveAllCommander() {
		for (CommanderBasicVO commanderBasicVO : commandersInfo) {
			TargetMementoManager mementoManager = targetMementoManager.get(commanderBasicVO.getName());
			attackers.get(commanderBasicVO.getName()).getTarget().restoreMemento(mementoManager.getMemento());
			// 击杀历史清除
			killedHistory.clear();
		}
	}
}
