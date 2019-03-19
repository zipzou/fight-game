/**
 * 
 */
package cn.nju.game.conf.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.nju.game.conf.Configuration;
import cn.nju.game.conf.util.FilePathUtil;

/**
 * 游戏配置
 * @author frank
 *
 */
public class GameConfiguration implements Configuration {

	private static final String GAME_ROLES_ONLINE = "game/roles/online/";
	private static final String GAME_SKILL_SKILL = "game/skill/";
	private static final String GAME_WEAPON_WEAPON = "game/weapon/";
	private static final String GAME_EQUIPMENT_EQUIPMENT = "game/equipment/";
	private static final String GAME_ROLES_ROLES = "game/roles/";
	public static final String ONLINE_ROLES_FILE = "onlineRoles";
	public static final String SKILL_FILE = "skillFile";
	public static final String WEAPON_FILE = "weaponFile";
	public static final String EQUIPMENT_FILE = "equipmentFile";
	public static final String ROLES_FILE = "rolesFile";
	private static final Logger LOG = Logger.getLogger(GameConfiguration.class);
	private JSONObject configJson;														// 配置信息对象
	private String confPath; 															// 配置文件路径
	
	private static class GameConfigurationHolder {
		private static final Configuration _INSTANCE = new GameConfiguration();
	}
	
	/**
	 * 应用程序配置
	 * @return 应用程序配置接口
	 */
	public static Configuration sharedConfiguration() {
		return GameConfigurationHolder._INSTANCE;
	}
	
	// 加载默认配置
	private Map<String, Object> getDefaultConfig() {
		Hashtable<String, Object> configMap = new Hashtable<String, Object>();
		configMap.put(ROLES_FILE, GAME_ROLES_ROLES);
		configMap.put(EQUIPMENT_FILE, GAME_EQUIPMENT_EQUIPMENT);
		configMap.put(WEAPON_FILE, GAME_WEAPON_WEAPON);
		configMap.put(SKILL_FILE, GAME_SKILL_SKILL);
		configMap.put(ONLINE_ROLES_FILE, GAME_ROLES_ONLINE);
		return configMap;
	}
	
	// 检查配置文件和默认配置是否统一，并将缺失的默认配置，注入到对象中
	private void checkDeafultAndInject() {
		Map<String, Object> defaultConfig = getDefaultConfig();
		Set<Entry<String, Object>> configEntrySet = defaultConfig.entrySet();
		for (Entry<String, Object> entry : configEntrySet) {
			String curKey = entry.getKey();
			if (!configJson.containsKey(curKey)) {
				configJson.put(curKey, entry.getValue()); // 注入默认参数
			}
		}
	}
	
	private GameConfiguration() {
		// 配置文件
		String parentPath = FilePathUtil.mkdir("game/conf");
		File confFile = new File(new File(parentPath), "config.json");
		confPath = confFile.getAbsolutePath();
		if (!confFile.exists()) {
			// 添加默认配置
			configJson = new JSONObject(getDefaultConfig()); // 默认配置
			String confString = JSON.toJSONString(configJson);
			// 存储默认配置
			try {
				FileOutputStream confOutStream = new FileOutputStream(confFile);
				IOUtils.write(confString, confOutStream, "utf-8");
				if (LOG.isInfoEnabled()) {
					LOG.info("saved default configuration file to : " + confPath);
				}
				confOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 读取默认配置
			try {
				FileInputStream confInputStream = new FileInputStream(confFile);
				String confJsonString = IOUtils.toString(confInputStream, "utf-8");
				configJson = JSON.parseObject(confJsonString);
				confInputStream.close();
				if (LOG.isInfoEnabled()) {
					LOG.info("loaded configuration file from: " + confPath);
				}
				// 比对配置文件及默认配置
				checkDeafultAndInject();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.nju.game.conf.Configuration#read(java.lang.String)
	 */
	public Object read(String key) {
		return configJson.get(key);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.conf.Configuration#save(java.lang.String, java.lang.Object)
	 */
	public void save(String key, Object value) throws IOException {
		configJson.put(key, value);
		OutputStream confOutStream = new FileOutputStream(confPath);
		IOUtils.write(configJson.toJSONString(), confOutStream, "utf-8");
	}

}
