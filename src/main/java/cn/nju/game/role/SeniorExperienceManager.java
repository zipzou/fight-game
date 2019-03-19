/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;

/**
 * 高级经验管理者
 * @author frank
 *
 */
public class SeniorExperienceManager extends AbstractExprienceManager {

	public SeniorExperienceManager(LevelManager next) {
		super(next);
		strategy = new SeniorStrategy();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.AbstractExprienceManager#handleExprience(cn.nju.game.service.ExprienceCollector)
	 */
	@Override
	public void handleExprience(ExprienceCollector collector) {
		if (10 < collector.getLevel(forName())) {
			super.handleExprience(collector);
		} else {
			if (null != next) {
				next.handleExprience(collector);
			}
		}
	}

}
