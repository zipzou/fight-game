package cn.nju.game.role;
/**
 * 物理怪
 */
public final class PhysicalMonster extends Monster {

    /**
     * Default constructor
     */
    public PhysicalMonster() {
    	this.setHealth(200);
    	this.setPhysicalDamage(10);
    	this.setArmor(2);
    	this.setMagicalResistance(1);
    	this.setName("物理怪");
    }

}