/**
 * 
 */
package cn.nju.game.model.vo;

/**
 * 战士召唤师VO
 * @author frank
 *
 */
public class WarriorCommanderBasicVO extends CommanderBasicVO {

	private static final String WARRIOR = "战士";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6159660667339053915L;

	/* (non-Javadoc)
	 * @see cn.nju.game.model.vo.CommanderBasicVO#job()
	 */
	@Override
	public String getJob() {
		return WARRIOR;
	}

}
