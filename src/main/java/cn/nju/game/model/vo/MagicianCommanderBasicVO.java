/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * @author frank
 *
 */
public class MagicianCommanderBasicVO extends CommanderBasicVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8836547914751351830L;
	private static final String MAGICIAN = "法师";

	/* (non-Javadoc)
	 * @see cn.nju.game.model.vo.CommanderBasicVO#job()
	 */
	@Override
	public String getJob() {
		return MAGICIAN;
	}

}
