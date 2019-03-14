/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.model.vo.DefenceVO;

/**
 * 防御值计算外观
 * @author frank
 *
 */
public class DefenceComputorManager implements DefenceComputor {

	private DefenceComputor[] defences;
	/**
	 * @param defences
	 */
	protected DefenceComputorManager(DefenceComputor fromCommander, DefenceComputor fromEquipment) {
		super();
		defences = new DefenceComputor[2];
		defences[0] = fromCommander;
		defences[1] = fromEquipment;
	}



	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		DefenceVO result = new DefenceVO();
		for (DefenceComputor defenceComputor : defences) {
			result.plus(defenceComputor.compute());
		}
		return result;
	}

}
