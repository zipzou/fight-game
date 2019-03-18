/**
 * 
 */
package cn.nju.game.role;

import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/**
 * 竞技场中介
 * @author frank
 *
 */
public class StageFightMediator implements StagePartnerMediator {

	private static final Logger LOG = Logger.getLogger(StageFightMediator.class);
	
	private Set<StagePartner> partners;
	
	/**
	 * 
	 */
	public StageFightMediator() {
		super();
		partners = new TreeSet<StagePartner>();
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartnerMediator#sendAttack(cn.nju.game.role.StagePartner)
	 */
	public void sendAttack(StagePartner source) {
		for (StagePartner item : partners) {
			if (item != source) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Start attacking :" + source.getTarget().getName() + " -> " + item.getTarget().getName());
					LOG.info("The " + item.getTarget().getName() + "'s health is :" + item.getTarget().getHealth());
				}
				if (source instanceof CommanderPartner  && item != source) {
					item.attacked(source.getTarget(), ((CommanderPartner) source).getWeapon(), ((CommanderPartner) source).getEquipments(), ((CommanderPartner) source).getSkill());
				} else {
					item.attacked(source.getTarget(), null, null, null);
				}
				if (LOG.isInfoEnabled()) {
					LOG.info("Attack completed!");
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartnerMediator#register(cn.nju.game.role.StagePartner)
	 */
	public void register(StagePartner partner) {
		partner.setMediator(this);
		partners.add(partner);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartnerMediator#unregister(cn.nju.game.role.StagePartner)
	 */
	public void unregister(StagePartner partner) {
		partners.remove(partner);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.role.StagePartnerMediator#getAllPartners()
	 */
	public Iterable<StagePartner> getAllPartners() {
		return partners;
	}
	
}
