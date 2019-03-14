/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DefenceVO;
import cn.nju.game.role.Target;

/**
 * 召唤师防御值计算
 * @author frank
 *
 */
public class TargetDenfenceComputor implements DefenceComputor {

	private Target commander;
	
	/**
	 * 
	 */
	protected TargetDenfenceComputor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param commander
	 */
	protected TargetDenfenceComputor(Target commander) {
		super();
		this.commander = commander;
	}

	/**
	 * @return the commander
	 */
	public Target getCommander() {
		return commander;
	}

	/**
	 * @param commander the commander to set
	 */
	public void setCommander(Target commander) {
		this.commander = commander;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		int armor = commander.getArmor();
		int magicalResistance = commander.getMagicalResistance();
		DefenceVO denfence = new DefenceVO();
		denfence.setArmor(armor);
		denfence.setMagicalResistance(magicalResistance);
		return denfence;
	}

}
