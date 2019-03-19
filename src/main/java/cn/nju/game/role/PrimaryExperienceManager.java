/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;

/**
 * 初级经验管理者
 * @author frank
 *
 */
public class PrimaryExperienceManager extends AbstractExprienceManager {

	public PrimaryExperienceManager(LevelManager next) {
		super(next);
		strategy = new PrimaryLevelupStrategy();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.AbstractExprienceManager#handleExprience(cn.nju.game.service.ExprienceCollector)
	 */
	@Override
	public void handleExprience(ExprienceCollector collector) {
		if (4 < commander.getLevel()) {
			if (null != next) {
				next.handleExprience(collector);
			}
			return;
		}
		super.handleExprience(collector);
	}

}
