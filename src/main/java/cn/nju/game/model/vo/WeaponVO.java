/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * 武器Value Object
 * @author frank
 *
 */
public class WeaponVO {
	/**
     * 武器名
     */
    private String name;
    
    /**
     * 价格
     */
    private int price;
    
    /**
     * 武器伤害
     */
    private int damage;
    
    /**
     * 武器描述信息
     */
    private String description;

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
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
}
