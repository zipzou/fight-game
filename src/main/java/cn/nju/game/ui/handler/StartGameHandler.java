/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.nju.game.service.StageService;
import cn.nju.game.ui.FightStageFrame;
import cn.nju.game.ui.MonsterConfigFrame;
import cn.nju.game.ui.util.BoundsUtil;

/**
 * 开始游戏处理类
 * @author frank
 *
 */
public class StartGameHandler extends MouseAdapter {

	private StageService stageService;
	private MonsterConfigFrame context;
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		int physicalCount = (Integer) context.getSpinnerPhysicalMonsterCount().getValue();
		int magicalCount = (Integer) context.getSpinnerMagicalMonsterCount().getValue();
		stageService.registerPyhsicalPartner(physicalCount);
		stageService.registerMagicalPartner(magicalCount);
		stageService.ready();
		
		
		FightStageFrame fightStageFrame = new FightStageFrame(stageService);
		fightStageFrame.setTitle("竞技场");
		Rectangle centerBound = BoundsUtil.getCenterOwnerBounds(600, 600);
		fightStageFrame.setBounds(centerBound);
		fightStageFrame.setVisible(true);
		context.dispose();
	}

	/**
	 * @return the stageService
	 */
	public StageService getStageService() {
		return stageService;
	}

	/**
	 * @param stageService the stageService to set
	 */
	public void setStageService(StageService stageService) {
		this.stageService = stageService;
	}

	/**
	 * @return the context
	 */
	public MonsterConfigFrame getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(MonsterConfigFrame context) {
		this.context = context;
	}

}
