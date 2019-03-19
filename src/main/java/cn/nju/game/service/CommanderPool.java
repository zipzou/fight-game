/**
 * 
 */
package cn.nju.game.service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.conf.util.FilePathUtil;
import cn.nju.game.role.Commander;
import cn.nju.game.role.factory.MagicianFactory;
import cn.nju.game.role.factory.WarriorFactory;
import cn.nju.game.util.ObjectiveSerializeUtil;

/**
 * 召唤师共享池，用于保证同一个召唤师，只有一个
 * @author frank
 *
 */
public class CommanderPool {

	private static final String SUBFIX = ".dt";
	private static final Logger LOG = Logger.getLogger(CommanderPool.class);
	private Set<Commander> commanders;
	
	public CommanderPool() {
		super();
		commanders = new HashSet<Commander>();
		// 从文件加载信息
		loadCommandersFromFile();
	}
	
	/**
	 * 从文件加载召唤师
	 */
	protected void loadCommandersFromFile() {
		String rolesFilePath = GameConfiguration.sharedConfiguration().read(GameConfiguration.ROLES_FILE).toString();
		File rolesPath = new File(rolesFilePath);
		if (!rolesPath.exists()) {
			FilePathUtil.mkdir(rolesFilePath);
		}
		File[] rolesFiles = rolesPath.listFiles();
		for (File roleFile : rolesFiles) {
			if ((roleFile.isDirectory() || !roleFile.getName().endsWith(SUBFIX))) {
				continue;
			}
			try {
				Commander commander = ObjectiveSerializeUtil.unserialize(rolesFilePath, roleFile.getName().replace(SUBFIX, ""));
				commanders.add(commander);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
			serialize(); // save to files
		}
		return target;
	}
	
	/**
	 * 获取召唤师
	 * @param name 召唤师名
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
	
	@Deprecated
	public void release(Commander commander) {
		commanders.add(commander);
	}
	
	private void serialize() {
		for (Commander commander : commanders) {
			String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.ROLES_FILE).toString();
			try {
				ObjectiveSerializeUtil.serialize(folder, commander.getName(), commander);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
