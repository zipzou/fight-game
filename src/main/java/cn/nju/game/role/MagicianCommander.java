package cn.nju.game.role;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.MagicianCommanderBasicVO;

/**
 * 魔法师召唤师
 */
public class MagicianCommander extends Commander {

	public static final Logger LOG = Logger.getLogger(MagicianCommander.class);
	/**
	 * 法师召唤师构建器
	 * @author frank
	 *
	 */
	private static class MagicianCommanderBuilder extends Commander.WarriorCommanderBuilder {
		private static final String MAGICIAN_JPG = "magician.jpg";

		public MagicianCommanderBuilder() {
			super();
			commander = new MagicianCommander();
			commander.setIcon(MAGICIAN_JPG);
		}
	}
	
    /**
     * Default constructor
     */
    protected MagicianCommander() {
    }
    
    /**
     * 获取召唤师构建器
     * @return 召唤师构建器
     */
    public static CommanderBuilder builder() {
    	if (LOG.isInfoEnabled()) {
    		LOG.info("Start magican commander builder...");
    	}
    	return new MagicianCommanderBuilder();
    }

	@Override
	public CommanderBasicVO getBasicVO() {
		MagicianCommanderBasicVO magicianCommanderBasicVO = new DozerBeanMapper().map(this, MagicianCommanderBasicVO.class);
		return magicianCommanderBasicVO;
	}

}