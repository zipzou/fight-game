/**
 * 
 */
package cn.nju.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cn.nju.game.role.Commander;

/**
 * 在线召唤师
 * @author frank
 *
 */
public final class OnlineCommander {

	private static final Logger LOG = Logger.getLogger(OnlineCommander.class);
	
	private List<Commander> commanders;
	private CommanderPool pool;
	
	private static final class OnlineCommanderHolder {
		private static final OnlineCommander _INSTANCE = new OnlineCommander();
	}
	
	private OnlineCommander() {
		commanders = new ArrayList<Commander>();
		pool = new CommanderPool();
	}
	
	public static final OnlineCommander sharedCommanders() {
		return OnlineCommanderHolder._INSTANCE;
	}
	
	/**
	 * 查找已上线的召唤师
	 * @param name 召唤师名
	 * @return 召唤师
	 */
	public Commander get(String name) {
		for (Commander item : commanders) {
			if (name.equals(item.getName())) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Found online commander: " + name);
				}
				return item;
			}
		}
		return null;
	}
	
	public void online(String name) {
		Commander commander = pool.get(name);
		if (null != commander) {
			commanders.add(commander);
			if (LOG.isInfoEnabled()) {
				LOG.info("commander: " + name + " is online!");
			}
		}
	}
	
	public void offline(String name) {
		Iterator<Commander> commandersIt = commanders.iterator();
		while (commandersIt.hasNext()) {
			Commander curCommander = commandersIt.next();
			if (name.equals(curCommander.getName())) {
				commandersIt.remove();
				pool.release(curCommander);
				if (LOG.isInfoEnabled()) {
					LOG.info("commander: " + name + "is offline!");
				}
				break;
			}
		}
	}

	/**
	 * @return the pool
	 */
	public CommanderPool getPool() {
		return pool;
	}
	
}
