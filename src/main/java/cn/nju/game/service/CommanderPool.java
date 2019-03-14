/**
 * 
 */
package cn.nju.game.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.nju.game.role.Commander;
import cn.nju.game.role.factory.MagicianFactory;
import cn.nju.game.role.factory.WarriorFactory;

/**
 * 召唤师共享池，用于保证同一个召唤师，只有一个
 * @author frank
 *
 */
public class CommanderPool {

	private static final Logger LOG = Logger.getLogger(CommanderPool.class);
	private Set<Commander> commanders;
	
	public CommanderPool() {
		super();
		commanders = new HashSet<Commander>();
	}

	/**
	 * 获取召唤师
	 * @param name 召唤师名
	 * @param commandType 召唤师职业
	 * @return 召唤师
	 */
	public Commander get(String name, int commandType) {
		Commander target = null;
		target = get(name);
		if (null == target) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Not shared, to be create...");
			}
			if (Commander.WARRIOR_COMMANDER == commandType) {
				target = new WarriorFactory().produceCommander(name);
			} else if (Commander.MAGICIAN_COMMANDER == commandType) {
				target = new MagicianFactory().produceCommander(name);
			}
			commanders.add(target);
			if (LOG.isInfoEnabled()) {
				LOG.info("Commander Cached!");
			}
		}
		return target;
	}
	
	/**
	 * 获取召唤师
	 * @param name 召唤师名
	 * @param commandType 召唤师职业
	 * @return 召唤师
	 */
	public Commander get(String name) {
		Iterator<Commander> commanderIt = commanders.iterator();
		Commander target = null;
		while (commanderIt.hasNext()) {
			Commander curCommander = commanderIt.next();
			if (name.equals(curCommander.getName())) {
				target = curCommander;
				if (LOG.isInfoEnabled()) {
					LOG.info("Found commander: " + name);
				}
				break;
			}
		}
		return target;
	}
	
	public void release(Commander commander) {
		commanders.add(commander);
	}
}
