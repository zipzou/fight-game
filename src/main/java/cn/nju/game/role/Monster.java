package cn.nju.game.role;

/**
 * 怪
 */
public abstract class Monster extends Target implements Damageable {

    /**
     * Default constructor
     */
    public Monster() {
    }

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Target#clone()
	 */
	@Override
	public Monster clone() throws CloneNotSupportedException {
		return (Monster) super.clone();
	}

    
}