package cn.nju.game.weapon;

import org.dozer.DozerBeanMapper;

import cn.nju.game.model.vo.WeaponVO;

/**
 * 武器，根据职业不同，武器造成的伤害类型会不同
 */
public class Weapon implements DamageComputable, WeaponInfo {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9034870455599140870L;

	/**
     * Default constructor
     */
    public Weapon() {
    }

    /**
     * 武器名
     */
    protected String name;
    
    /**
     * 价格
     */
    protected int price;
    
    /**
     * 武器伤害
     */
    protected int damage;
    
    /**
     * 武器描述信息
     */
    protected String description;

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.DamageComputable#computeDamage()
	 */
	public int computeDamage() {
		return damage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		description = getName() + "，将造成" + getDamage() + "的基础伤害，并同时造成" + computeDamage() + "的自适应物理/魔法伤害";
		return description;
	}

	/**
	 * @param description the description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param description the description to set
	 */
	public void weapon(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.weapon.WeaponInfo#getWeaponInfo()
	 */
	public WeaponVO getWeaponInfo() {
		return new DozerBeanMapper().map(this, WeaponVO.class);
	}
}