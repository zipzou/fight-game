package cn.nju.game.role;

import java.io.IOException;

import org.apache.log4j.Logger;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.util.ObjectiveSerializeUtil;
import cn.nju.game.weapon.Weapon;

/**
 * 召唤师
 */
public abstract class Commander extends Target implements Recoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -435971933032542845L;

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
	protected static abstract class AbstractCommanderBuilder implements CommanderBuilder {

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
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * 获取基本信息VO实体
	 * @return 基本信息实体
	 */
	public abstract CommanderBasicVO getBasicVO();
	
	/**
	 * 获取武器信息
	 * @return 武器信息
	 */
	public Weapon getWeapon() {
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString();
		Weapon weapon = null;
		try {
			weapon = ObjectiveSerializeUtil.unserialize(folder, getName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return weapon;
	}
	
	/* (non-Javadoc)
	 * @see cn.nju.game.role.Target#clone()
	 */
	@Override
	public Commander clone() throws CloneNotSupportedException {
		return (Commander) super.clone();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Recoverable#improveEnergy(int)
	 */
	public void improveEnergy(int energyVal) {
		this.energy += energyVal;
		setChanged();
		notifyObservers(this);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Target#growUp()
	 */
	@Override
	public int growUp() {
		return super.growUp();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Target#getKilledExp()
	 */
	@Override
	public int getKilledExp() {
		return (int) (20 * Math.pow(1.2, getLevel()));
	}

}