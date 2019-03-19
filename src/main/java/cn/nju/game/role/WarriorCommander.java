package cn.nju.game.role;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.WarriorCommanderBasicVO;
import cn.nju.game.util.ObjectiveSerializeUtil;
import cn.nju.game.weapon.Weapon;

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
	private static class WarriorCommanderBuilder extends Commander.AbstractCommanderBuilder {
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

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Commander#getWeaponVO()
	 */
	@Override
	public Weapon getWeapon() {
		Weapon weapon = super.getWeapon();
		if (null == weapon) {
			weapon = new Weapon();
			weapon.setName("长剑");
			weapon.setDamage(10);
//			weapon.setDescription("长剑，适合战士使用，将对敌方目标造成" + weapon.computeDamage() + "点的物理伤害");
			String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString();
			try {
				ObjectiveSerializeUtil.serialize(folder, getName(), weapon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return weapon;
	}
    
}