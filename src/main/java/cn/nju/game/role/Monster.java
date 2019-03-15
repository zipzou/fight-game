package cn.nju.game.role;

/**
 * 怪
 */
public abstract class Monster extends Target implements Damageable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8674040199369448829L;

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