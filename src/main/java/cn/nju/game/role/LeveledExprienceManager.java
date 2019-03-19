/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;
import cn.nju.game.service.OnlineCommander;

/**
 * 处理升级需求的管理者
 * @author frank
 *
 */
public class LeveledExprienceManager implements LevelManager {

	/**
	 * 下一个处理经验的管理者
	 */
	private LevelManager nextLevelManager;
	private Commander commander;
	
	public LeveledExprienceManager(LevelManager nextLevelManager) {
		super();
		this.nextLevelManager = nextLevelManager;
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.role.LevelManager#handleExprience(cn.nju.game.service.ExprienceCollector)
	 */
	public void handleExprience(ExprienceCollector collector) {
		double expRate = Math.pow(1.5, collector.getLevel(forName()));
		int nextLevel = (int) (expRate * LevelManager.UNIT_LEVEL_EXPRIENCE);
		if (collector.getExprience(forName()) + commander.getExpirience() > nextLevel) {
			commander.setLevel(commander.getLevel() + 1);
			// 重置经验值
			int exp = collector.getExprience(forName()) + commander.getExpirience() - nextLevel;
			commander.setExpirience(exp);
			// 同时更新缓冲池中的信息
			Commander onlineCommander = OnlineCommander.sharedCommanders().get(commander.getName());
			onlineCommander.setExpirience(exp);
			onlineCommander.setLevel(exp);
		} else {
			if (null != nextLevelManager) {
				nextLevelManager.handleExprience(collector);
			}
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

}
