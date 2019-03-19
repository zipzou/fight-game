/**
 * 
 */
package cn.nju.game.service;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.role.Target;

/**
 * 竞技场服务接口
 * @author frank
 *
 */
public interface StageService {

	/**
	 * 注册参与者
	 * @param commanderName 召唤师名
	 * @param equipments 召唤师所选的装备
	 * @param skills 召唤师所选的技能
	 */
	public void registerPartner(String commanderName, List<String> equipments, List<SkillVO> skills);
	/**
	 * 注册物理野怪加入战斗
	 * @param count 数量
	 */
	public void registerPyhsicalPartner(int count);
	/**
	 * 注册魔法野怪加入战斗
	 * @param count 数量
	 */
	public void registerMagicalPartner(int count);
	
	/**
	 * 获取参与者的装备信息
	 * @param i 第i个召唤师
	 * @return 参与者装备名称
	 */
	public List<String> getEquipments(int i);
	
	/**
	 * 获取参与者学习的技能信息
	 * @param i 第i个召唤师
	 * @return 技能信息列表
	 */
	public List<SkillVO> getSkills(int i);
	
	/**
	 * 获取召唤师基本信息
	 * @param i 第i个召唤师
	 * @return 召唤师信息
	 */
	public CommanderBasicVO getCommanderInfo(int i);
	
	/**
	 * 准备就绪
	 */
	public void ready();
	
	/**
	 * 获取当前召唤师的健康值
	 * @param name
	 * @return 当前健康值
	 */
	public int getCurrentHealth(String name);
	
	/**
	 * 注册观察者以便于监听参与对象的状态
	 * @param observer 观察者
	 */
	public void registerPartnerObserver(Observer observer);
	
	/**
	 * 是否为第一个召唤师
	 * @param o 被观察的对象
	 * @return 如果是，则为<i>true</i>，否则为<i>false</i>
	 */
	public boolean isFirstPartner(Observable o);
	/**
	 * 是否为第二个召唤师
	 * @param o 被观察的对象
	 * @return 如果是，则为<i>true</i>，否则为<i>false</i>
	 */
	public boolean isSecondPartner(Observable o);
	/**
	 * 是否为野怪
	 * @param o 被观察的对象
	 * @return 如果是，则为<i>true</i>，否则为<i>false</i>
	 */
	public boolean isMonster(Observable o);
	
	/**
	 * 获取被观察者的健康状态
	 * @param o 被观察者，即野怪或召唤师
	 * @return 野怪或召唤师的健康值
	 */
	public int getHealth(Observable o);
	
	/**
	 * 获取被观察者的能量状态
	 * @param o 被观察者，即野怪或召唤师
	 * @return 野怪或召唤师的能量值
	 */
	public int getEnergy(Observable o);
	
	/**
	 * 参与者攻击
	 * @param i 第i个参与者
	 * @param skills 施放的技能
	 */
	public void attack(int i, SkillService skills);
	
	/**
	 * 清除已经死亡的对象
	 */
	public void clearDied();
	
	/**
	 * 获取游戏获胜者
	 * @return 游戏获胜者信息
	 */
	public CommanderBasicVO getWinner();
	
	/**
	 * 收集经验
	 * @param name 经验获得者
	 * @param partner 经验获取来源
	 */
	public void collectExprience(String name, Target partner);
	
	/**
	 * 复活所有召唤师
	 */
	public void reviveAllCommander();
}
