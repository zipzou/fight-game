/**
 * 
 */
package cn.nju.game.role;

import cn.nju.game.service.ExprienceCollector;

/**
 * 成长期经验管理者
 * @author frank
 *
 */
public class JuniorExperienceManager extends AbstractExprienceManager {

	public JuniorExperienceManager(LevelManager next) {
		super(next);
		strategy = new JuniorStrategy();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.AbstractExprienceManager#handleExprience(cn.nju.game.service.ExprienceCollector)
	 */
	@Override
	public void handleExprience(ExprienceCollector collector) {
		// 处理4-10级
		if (4 <= collector.getLevel(forName()) && 10 >= collector.getLevel(forName())) {
			super.handleExprience(collector);
		} else {
			if (null != next) {
				next.handleExprience(collector);
			}
		}
	}

}
