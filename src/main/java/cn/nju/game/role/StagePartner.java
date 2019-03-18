/**
 * 
 */
package cn.nju.game.role;

/**
 * 角斗场参与角色
 * @author frank
 *
 */
public abstract class StagePartner implements Attacker, Attacked, Comparable<StagePartner> {
	private StagePartnerMediator mediator;
	/**
	 * 参与竞技场的召唤师
	 */
	private Target target;
	/**
	 * @return the target
	 */
	public Target getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Target target) {
		this.target = target;
	}
	/**
	 * @return the mediator
	 */
	public StagePartnerMediator getMediator() {
		return mediator;
	}

	/**
	 * @param mediator the mediator to set
	 */
	public void setMediator(StagePartnerMediator mediator) {
		this.mediator = mediator;
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.Attacker#attack(cn.nju.game.role.Target)
	 */
	public void attack() {
		getMediator().sendAttack(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(StagePartner o) {
		return getTarget().getName().compareTo(o.getTarget().getName());
	}

//	/* (non-Javadoc)
//	 * @see cn.nju.game.role.Attacker#attack(cn.nju.game.skill.ComposedSkill)
//	 */
//	public void attack(ComposedSkill skills) {
//		getMediator().sendAttack(this);
//	}
}
