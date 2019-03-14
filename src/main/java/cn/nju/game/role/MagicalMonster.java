package cn.nju.game.role;

/**
 * 魔法怪
 */
public final class MagicalMonster extends Monster {

    /**
     * Default constructor
     */
    public MagicalMonster() {
    	this.setHealth(200);
    	this.setMagicDamage(10);
    	this.setName("魔法怪");
    }

}