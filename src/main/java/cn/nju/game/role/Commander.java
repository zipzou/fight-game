package cn.nju.game.role;

import org.apache.log4j.Logger;

import cn.nju.game.model.vo.CommanderBasicVO;

/**
 * 召唤师
 */
public abstract class Commander extends Target implements Recoverable {

	private static final Logger LOG = Logger.getLogger(Commander.class);
	
	/**
	 * 战士
	 */
	public static final int WARRIOR_COMMANDER = 0;
	/**
	 * 法师
	 */
	public static final int MAGICIAN_COMMANDER = 1;
	
	/**
	 * 召唤师构建者
	 * @author frank
	 *
	 */
	protected static abstract class WarriorCommanderBuilder implements CommanderBuilder {

		protected Commander commander;
		
		public CommanderBuilder health(int health) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander health:" + health);
			}
			commander.setHealth(health);
			return this;
		}

		public CommanderBuilder name(String name) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander name:" + name);
			}
			commander.setName(name);
			return this;
		}

		public CommanderBuilder physicalDamage(int damage) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander physical damage:" + damage);
			}
			commander.setPhysicalDamage(damage);
			return this;
		}

		public CommanderBuilder magicalDamage(int damage) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander magicial damage:" + damage);
			}
			commander.setMagicDamage(damage);
			return this;
		}

		public CommanderBuilder armor(int armor) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander armor:" + armor);
			}
			commander.setArmor(armor);
			return this;
		}

		public CommanderBuilder magicalResistance(int resistance) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander magical resistance:" + resistance);
			}
			commander.setMagicalResistance(resistance);
			return this;
		}

		public CommanderBuilder energy(int energy) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander energy:" + energy);
			}
			commander.setEnergy(energy);
			return this;
		}

		public CommanderBuilder expirence(int expirence) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander expirence:" + expirence);
			}
			commander.setExpirience(expirence);
			return this;
		}

		public Commander build() {
			if (LOG.isInfoEnabled()) {
				LOG.info("Build commander completed.");
			}
			commander.setLevel(1);
			return commander;
		}

	}
	
    /**
     * Default constructor
     */
    public Commander() {
    }

    /**
     * 能量值
     */
    private int energy;

    /**
     * 经验值
     */
    private int expirience;

	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * @return the expirience
	 */
	public int getExpirience() {
		return expirience;
	}

	/**
	 * @param energy the energy to set
	 */
	protected void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * @param expirience the expirience to set
	 */
	protected void setExpirience(int expirience) {
		this.expirience = expirience;
	}
	
	/**
	 * 获取基本信息VO实体
	 * @return 基本信息实体
	 */
	public abstract CommanderBasicVO getBasicVO();
	
}