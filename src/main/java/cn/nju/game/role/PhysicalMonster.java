package cn.nju.game.role;
/**
 * 物理怪
 */
public final class PhysicalMonster extends Monster {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6512560875408857576L;

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