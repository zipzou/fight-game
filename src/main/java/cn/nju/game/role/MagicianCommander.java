package cn.nju.game.role;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.MagicianCommanderBasicVO;
import cn.nju.game.weapon.Weapon;

/**
 * 魔法师召唤师
 */
public class MagicianCommander extends Commander {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063143656476285021L;
	public static final Logger LOG = Logger.getLogger(MagicianCommander.class);
	/**
	 * 法师召唤师构建器
	 * @author frank
	 *
	 */
	private static class MagicianCommanderBuilder extends Commander.AbstractCommanderBuilder {
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

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Commander#getWeaponVO()
	 */
	@Override
	public Weapon getWeapon() {
		Weapon weapon = new Weapon();
		weapon.setName("符文典籍");
		weapon.setDamage(10);
//		weapon.setDescription("长剑，适合法师使用，将对敌方目标造成" + weapon.getDamage() + "点的魔法伤害");
		return weapon;
	}

}