/**
 * 
 */
package cn.nju.game.fight;

import cn.nju.game.equip.Bag;
import cn.nju.game.model.vo.DefenceVO;
import cn.nju.game.role.Target;

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
	public DefenceComputorManager(Target fromTarget, Bag fromEquipment) {
		super();
		defences = new DefenceComputor[2];
		defences[0] = new TargetDenfenceComputor(fromTarget);
		defences[1] = new EquipmentDenfenceComputor(fromEquipment);
	}



	/* (non-Javadoc)
	 * @see cn.nju.game.fight.DefenceComputor#compute()
	 */
	public DefenceVO compute() {
		DefenceVO result = new DefenceVO();
		for (DefenceComputor defenceComputor : defences) {
			result = result.plus(defenceComputor.compute());
		}
		return result;
	}

}
