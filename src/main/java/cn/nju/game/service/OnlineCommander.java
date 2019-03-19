/**
 * 
 */
package cn.nju.game.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.conf.util.FilePathUtil;
import cn.nju.game.role.Commander;

/**
 * 在线召唤师
 * @author frank
 *
 */
public final class OnlineCommander {
	private static final Logger LOG = Logger.getLogger(OnlineCommander.class);
	
	private List<Commander> onlineCommanders;
	private CommanderPool pool;
	
	private static final class OnlineCommanderHolder {
		private static final OnlineCommander _INSTANCE = new OnlineCommander();
	}
	
	private OnlineCommander() {
//		super(new File(OnlineCommanderHolder.PATH), null);
		onlineCommanders = new ArrayList<Commander>();
		pool = new CommanderPool();
		String path = GameConfiguration.sharedConfiguration().read(GameConfiguration.ONLINE_ROLES_FILE).toString();
		path = FilePathUtil.mkdir(path);
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
		for (Commander item : onlineCommanders) {
			if (name.equals(item.getName())) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Found online commander: " + name);
				}
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 获取所有的在线召唤师
	 * @return 在线召唤师
	 */
	public List<Commander> getAllOnlines() {
		return onlineCommanders;
	}
	
	public void online(String name) {
//		Commander commander = pool.get(name);
		JSONArray onlineJson = readOnlineCommanders();
		if (null == onlineJson) {
			onlineJson = new JSONArray();
		} else {
			for (Object onlineName : onlineJson) {
				Commander onlineCommander = pool.get(onlineName.toString());
				int indexOf = onlineCommanders.indexOf(onlineCommander);
				if (null != onlineCommander && -1 >= indexOf) {
					onlineCommanders.add(onlineCommander);
				}
			}
		}
		
		Commander onlineCommander = pool.get(name);
		int indexOf = onlineCommanders.indexOf(onlineCommander);
		if (null != onlineCommander && -1 >= indexOf) {
			onlineCommanders.add(onlineCommander);
		}
		
		List<String> names = new ArrayList<String>();
		for (Commander item : this.onlineCommanders) {
			names.add(item.getName());
		}
		
		try {
			File file = new File(GameConfiguration.sharedConfiguration().read(GameConfiguration.ONLINE_ROLES_FILE).toString());
			FileOutputStream outpuStream = new FileOutputStream(new File(file, "online.json"));
			IOUtils.write(JSON.toJSONString(names), outpuStream, "utf-8");
			outpuStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void offline(String name) {
		Iterator<Commander> commandersIt = onlineCommanders.iterator();
		while (commandersIt.hasNext()) {
			Commander curCommander = commandersIt.next();
			if (name.equals(curCommander.getName())) {
				commandersIt.remove();
//				pool.release(curCommander);
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

	public JSONArray readOnlineCommanders() {
		InputStream onlineInput;
		try {
			File file = new File(GameConfiguration.sharedConfiguration().read(GameConfiguration.ONLINE_ROLES_FILE).toString());
			File outpuFile = new File(file, "online.json");
			if (!outpuFile.exists()) {
				return null;
			}
			onlineInput = new FileInputStream(outpuFile);
			String onlineJsonStr = IOUtils.toString(onlineInput, "utf-8");
			if (null != onlineInput) {
				onlineInput.close();
			}
			JSONArray allOnlines = JSON.parseArray(onlineJsonStr);
			return allOnlines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
