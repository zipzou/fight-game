package cn.nju.game.role;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WarriorCommanderBasicVO;

/**
 * 战士召唤师
 */
public class WarriorCommander extends Commander {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202765091914779706L;
	private static final Logger LOG = Logger.getLogger(WarriorCommander.class);
	/**
	 * 召唤师构建者
	 * @author frank
	 *
	 */
	private static class WarriorCommanderBuilder extends Commander.WarriorCommanderBuilder {
		private static final String WARRIOR_JPG = "warrior.jpg";

		public WarriorCommanderBuilder() {
			super();
			commander = new WarriorCommander();
			commander.setIcon(WARRIOR_JPG);
		}
	}
	
    /**
     * Default constructor
     */
    protected WarriorCommander() {
    }

    /**
     * 获取召唤师构建者
     * @return
     */
    public static CommanderBuilder builder() {
    	if (LOG.isInfoEnabled()) {
    		LOG.info("Start warrior commander builder...");
    	}
    	return new WarriorCommanderBuilder();
    }

	@Override
	public CommanderBasicVO getBasicVO() {
		WarriorCommanderBasicVO warriorCommanderBasicVO = new DozerBeanMapper().map(this, WarriorCommanderBasicVO.class);
		return warriorCommanderBasicVO;
	}
    
}