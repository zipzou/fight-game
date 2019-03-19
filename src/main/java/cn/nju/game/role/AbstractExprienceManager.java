/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;
import cn.nju.game.service.OnlineCommander;

/**
 * 抽象经验管理员
 * @author frank
 *
 */
public abstract class AbstractExprienceManager implements LevelManager {
	/**
	 * 下一个处理者
	 */
	protected LevelManager next;
	protected LevelUpStrategy strategy;
	protected Commander commander;
	public AbstractExprienceManager(LevelManager next) {
		super();
		this.next = next;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelManager#handleExprience(cn.nju.game.service.ExprienceCollector)
	 */
	public void handleExprience(ExprienceCollector collector) {
		int nextLevel = strategy.getLevelupExperienceNeeded(collector.getLevel(forName()));
		if (collector.getExprience(forName()) + commander.getExpirience() < nextLevel) {
			// 处理经验值的累加
			if (null != commander) {
				commander.setExpirience(commander.getExpirience() + collector.getExprience(forName()));
				Commander onlineCommander = OnlineCommander.sharedCommanders().get(commander.getName());
				onlineCommander.setExpirience(onlineCommander.getExpirience() + collector.getExprience(commander.getName()));
			}
		} else {
			commander.setLevel(commander.getLevel() + 1);
			// 重置经验值
			int exp = collector.getExprience(forName()) + commander.getExpirience() - nextLevel;
			commander.setExpirience(exp);
			// 同时更新缓冲池中的信息
			Commander onlineCommander = OnlineCommander.sharedCommanders().get(commander.getName());
			onlineCommander.setExpirience(exp);
			onlineCommander.setLevel(commander.getLevel());
		}
	}
	/* (non-Javadoc)
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
	 * @param commander the commander to set
	 */
	public void setCommander(Commander commander) {
		this.commander = commander;
	}
	/**
	 * @return the strategy
	 */
	public LevelUpStrategy getStrategy() {
		return strategy;
	}
	
}
