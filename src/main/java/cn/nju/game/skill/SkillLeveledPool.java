/**
 * 
 */
package cn.nju.game.skill;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 分级的技能缓冲池
 * @author frank
 *
 */
public class SkillLeveledPool implements SkillPool {

	private static final String KEY_BASED = "based";
	private static final String KEY_ENERGY = "energy";
	private static final String KEY_MAGICAL_RESISTANCE = "magicalResistance";
	private static final String KEY_MAGICAL_DAMAGE = "magicalDamage";
	private static final String KEY_ARMOR = "armor";
	private static final String KEY_PHYSICAL_DAMAGE = "physicalDamage";
	private static final String KEY_LEVEL = "level";
	private static final String KEY_NAME = "name";
	private static final String UTF_8_CHARSET = "utf-8";
	private static final String FILE_SKILL_JSON = "skill.json";
	private static final String KEY_SKILLS = "skills";
	private Map<String, Map<Integer, Skill>> skillPool;
	private static final Logger LOG = Logger.getLogger(SkillLeveledPool.class);
	private static final class SkillLeveledPoolHolder {
		private static SkillLeveledPool _INSTANCE = new SkillLeveledPool();
	}
	
	public static SkillLeveledPool sharedPool() {
		return SkillLeveledPoolHolder._INSTANCE;
	}
	/**
	 * 
	 */
	private SkillLeveledPool() {
		super();
		skillPool = new Hashtable<String, Map<Integer,Skill>>();
		try {
			loadFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadFromFile() throws IOException {
		if (LOG.isInfoEnabled()) {
			LOG.info("Start loading skill info from file...");
		}
		InputStream skillInput = SkillPool.class.getResourceAsStream(FILE_SKILL_JSON);
		String skillJsonString = IOUtils.toString(skillInput, UTF_8_CHARSET);
		skillInput.close();
		JSONObject skillMapped = JSON.parseObject(skillJsonString);
		if (LOG.isInfoEnabled()) {
			LOG.info("File loaded...");
		}
		
		JSONArray skillLevelMap = skillMapped.getJSONArray(KEY_SKILLS);
		for (Object item : skillLevelMap) {
			JSONObject jsonItem = (JSONObject) item;
			String name = jsonItem.getString(KEY_NAME);
			BasedSkill basedSkill = new BasedSkill(null, name);
			
			if (LOG.isInfoEnabled()) {
				LOG.info("Loading skill : " + name);
			}
			
			JSONObject levelJsonObject = jsonItem.getJSONObject(KEY_LEVEL);
			Map<Integer, Skill> levels = new Hashtable<Integer, Skill>();
			levels.put(0, basedSkill);
			skillPool.put(name, levels);
			for (int i = 1; i <= 4; i++) {
				JSONObject skillJson = (JSONObject) levelJsonObject.get(i + "");
				
				LeveledSkill leveledSkill = new LeveledSkill(levels.get(i - 1), skillJson.getIntValue(KEY_PHYSICAL_DAMAGE), skillJson.getIntValue(KEY_ARMOR), skillJson.getIntValue(KEY_MAGICAL_DAMAGE), skillJson.getIntValue(KEY_MAGICAL_RESISTANCE), skillJson.getIntValue(KEY_ENERGY), i - basedSkill.getLevel());
				
				levels.put(i, leveledSkill);
			}
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Loading skill completed! ");
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.nju.game.skill.SkillPool#getAllSkills()
	 */
	public List<String> getAllSkills() {
		Set<String> skillNameSet = skillPool.keySet();
		return new ArrayList<String>(skillNameSet);
	}
	/* (non-Javadoc)
	 * @see cn.nju.game.skill.SkillPool#getSkill(java.lang.String, int)
	 */
	public Skill getSkill(String name, int level) {
		return skillPool.get(name).get(level);
	}
	
	/**
	 * 获取基础技能
	 * @return 技能
	 */ 
	public Skill getBased() {
		if (skillPool.containsKey(KEY_BASED)) {
			return skillPool.get(KEY_BASED).get(0);
		} else {
			BasedSkill skill = new BasedSkill("普通技能", "普通攻击");
			Map<Integer, Skill> basedSkillMap = new HashMap<Integer, Skill>();
			skillPool.put(KEY_BASED, basedSkillMap);
			basedSkillMap.put(0, skill);
			return skill;
		}
	}

}
