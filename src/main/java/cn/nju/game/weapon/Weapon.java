package cn.nju.game.weapon;

/**
 * 武器，根据职业不同，武器造成的伤害类型会不同
 */
public class Weapon implements DamageComputable {

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
    
}