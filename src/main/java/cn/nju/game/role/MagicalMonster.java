package cn.nju.game.role;

/**
 * 魔法怪
 */
public final class MagicalMonster extends Monster {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3786714108015036312L;

	/**
     * Default constructor
     */
    public MagicalMonster() {
    	this.setHealth(200);
    	this.setMagicDamage(10);
    	this.setName("魔法怪");
    }

}