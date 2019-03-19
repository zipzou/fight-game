/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;
import cn.nju.game.service.OnlineCommander;

/**
 * 经验累加的管理者
 * 
 * @author frank
 *
 */
public class SummaryExprienceManager implements LevelManager {
	/**
	 * 基于责任链模式，分层次处理等级
	 */
	private LevelManager nextLevelManager;
	private Commander commander;

	public void handleExprience(ExprienceCollector collector) {
		double expRate = Math.pow(1.5, collector.getLevel(forName()));
		int nextLevel = (int) (expRate * LevelManager.UNIT_LEVEL_EXPRIENCE);
		if (collector.getExprience(forName()) + commander.getExpirience() < nextLevel) {
			// 处理经验值的累加
			if (null != commander) {
				commander.setExpirience(commander.getExpirience() + collector.getExprience(forName()));
				Commander onlineCommander = OnlineCommander.sharedCommanders().get(commander.getName());
				onlineCommander.setExpirience(onlineCommander.getExpirience() + collector.getExprience(commander.getName()));
			}
		} else {
			if (null != nextLevelManager) {
				nextLevelManager.handleExprience(collector);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.nju.game.role.LevelManager#forName()
	 */
	public String forName() {
		return commander.getName();
	}

	/**
	 * @return the commander
	 */
	public Commander getCommander() {
		return commander;
	}

	/**
	 * @param commander
	 *            the commander to set
	 */
	public void setCommander(Commander commander) {
		this.commander = commander;
	}

	public SummaryExprienceManager(LevelManager nextLevelManager) {
		super();
		this.nextLevelManager = nextLevelManager;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelManager#getStrategy()
	 */
	public LevelUpStrategy getStrategy() {
		// TODO Auto-generated method stub
		return null;
	}
}
