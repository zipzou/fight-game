package cn.nju.game.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.role.Commander;
import cn.nju.game.role.Target;
import cn.nju.game.service.SkillService;
import cn.nju.game.service.StageService;
import cn.nju.game.service.impl.SkillServiceImpl;
import cn.nju.game.ui.handler.AttackHandler;
import cn.nju.game.ui.handler.CancelSkillHandler;
import cn.nju.game.ui.handler.CommanderPartnerCancel;
import cn.nju.game.ui.state.FightState;
import cn.nju.game.ui.state.FirstPartnerOperationState;
import cn.nju.game.ui.state.MonsterOperationState;

public class FightStageFrame extends JFrame implements Observer {
	private final class SkillReadyHandler extends MouseAdapter {
		private final StageService thatStageService;
		private SkillService skillService;
		private final JList<String> from;
		private final JList<String> to;
		private int commanderIndex;

		private SkillReadyHandler(StageService thatStageService, JList<String> from, JList<String> to) {
			this.thatStageService = thatStageService;
			this.from = from;
			this.to = to;
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (2 <= e.getClickCount()) {
				// 双击
				int index = from.locationToIndex(e.getPoint());
				from.setSelectionInterval(index, index);
				SkillVO skillInfo = thatStageService.getSkills(commanderIndex).get(index);
				skillService.addSkill(skillInfo.getName(), skillInfo.getLevel());
				DefaultListModel<String> model = (DefaultListModel<String>) to.getModel();
				model.addElement(skillInfo.getName() + "(" + skillInfo.getLevel() + "级)");
			}
		}
	}

	private static final long serialVersionUID = 5429368522806549660L;
	private JPanel contentPane;
	private StageService stageService;
	private JPanel panelBagEquipments1;
	private JPanel panelBagEquipments2;
	private JPanel panelSkills2;
	private JPanel panelSkills1;
	private JLabel lblCommanderName2;
	private JProgressBar progressEnergy2;
	private JPanel panelCommanderInfo2;
	private JProgressBar progressEnergy1;
	private JProgressBar progressHealth1;
	private JLabel lblCommanderName1;
	private JProgressBar progressHealth2;
	private JList<String> listSkillReady1;
	private JList<String> listSkillToExec1;
	private JLabel label;
	private JButton buttonAttack1;
	private JList<String> listSkillReady2;
	private JLabel lblSkillArrow2;
	private JList<String> listSkillToExec2;
	private JButton buttonAttack2;
	private SkillService skillServiceForFir;
	private SkillService skillServiceForSec;
	private FightState curState;
	private JLabel lblHealthTip2;
	private JLabel lblEnergyTip2;
	private JLabel lblHealthTip1;
	private JLabel lblEnergyTip1;

	/**
	 * Create the frame.
	 */
	public FightStageFrame(StageService stageService) {
		this.stageService = stageService;
		this.skillServiceForFir = new SkillServiceImpl();
		this.skillServiceForSec = new SkillServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 8, 0));
		
		JPanel panelCommander1 = new JPanel();
		panelCommander1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "召唤师1", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelCommander1);
		panelCommander1.setLayout(new GridLayout(3, 1, 16, 0));
		
		panelBagEquipments1 = new JPanel();
		panelBagEquipments1.setBorder(new TitledBorder(null, "\u6211\u7684\u80CC\u5305", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander1.add(panelBagEquipments1);
		panelBagEquipments1.setLayout(new GridLayout(3, 3, 0, 0));
		
		panelSkills1 = new JPanel();
		panelSkills1.setBorder(new TitledBorder(null, "\u6211\u7684\u6280\u80FD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander1.add(panelSkills1);
		panelSkills1.setLayout(null);
		
		listSkillReady1 = new JList<String>();
		listSkillReady1.setBounds(7, 18, 83, 157);
		listSkillReady1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final StageService thatStageService = stageService;
		panelSkills1.add(listSkillReady1);
		
		label = new JLabel(">>");
		label.setBounds(95, 18, 20, 157);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panelSkills1.add(label);
		
		listSkillToExec1 = new JList<String>();
		listSkillToExec1.setBounds(120, 18, 83, 157);
		panelSkills1.add(listSkillToExec1);
		
		buttonAttack1 = new JButton("攻击");
		buttonAttack1.setBounds(205, 60, 75, 29);
		panelSkills1.add(buttonAttack1);
		
		JButton buttonCancel1 = new JButton("撤销");
		buttonCancel1.setBounds(205, 90, 75, 29);
		panelSkills1.add(buttonCancel1);
		
		
		JPanel panelCommanderInfo1 = new JPanel();
		panelCommanderInfo1.setBorder(new TitledBorder(null, "\u53EC\u5524\u5E08\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander1.add(panelCommanderInfo1);
		panelCommanderInfo1.setLayout(null);
		
		lblCommanderName1 = new JLabel("召唤师名");
		lblCommanderName1.setBounds(104, 25, 79, 35);
		panelCommanderInfo1.add(lblCommanderName1);
		
		progressHealth1 = new JProgressBar();
		progressHealth1.setValue(50);
		progressHealth1.setBounds(60, 69, 146, 20);
		panelCommanderInfo1.add(progressHealth1);
		
		JLabel lblHealth1 = new JLabel("生命值");
		lblHealth1.setBounds(10, 71, 46, 16);
		panelCommanderInfo1.add(lblHealth1);
		
		progressEnergy1 = new JProgressBar();
		progressEnergy1.setBounds(60, 101, 146, 20);
		panelCommanderInfo1.add(progressEnergy1);
		
		JLabel lblEnergy1 = new JLabel("能量值");
		lblEnergy1.setBounds(10, 103, 46, 16);
		panelCommanderInfo1.add(lblEnergy1);
		
		lblHealthTip1 = new JLabel("1/100");
		lblHealthTip1.setBounds(210, 71, 65, 16);
		panelCommanderInfo1.add(lblHealthTip1);
		
		lblEnergyTip1 = new JLabel("1/100");
		lblEnergyTip1.setBounds(210, 103, 65, 16);
		panelCommanderInfo1.add(lblEnergyTip1);
		
		JPanel panelCommander2 = new JPanel();
		panelCommander2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "召唤师2", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelCommander2);
		panelCommander2.setLayout(new GridLayout(3, 0, 0, 0));
		
		panelBagEquipments2 = new JPanel();
		panelBagEquipments2.setBorder(new TitledBorder(null, "\u6211\u7684\u80CC\u5305", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander2.add(panelBagEquipments2);
		panelBagEquipments2.setLayout(new GridLayout(3, 3, 0, 0));
		
		panelSkills2 = new JPanel();
		panelSkills2.setBorder(new TitledBorder(null, "\u6211\u7684\u6280\u80FD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander2.add(panelSkills2);
		panelSkills2.setLayout(null);
		
		listSkillReady2 = new JList<String>();
		listSkillReady2.setBounds(7, 18, 83, 157);
		listSkillReady2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelSkills2.add(listSkillReady2);
		
		lblSkillArrow2 = new JLabel(">>");
		lblSkillArrow2.setBounds(95, 18, 20, 157);
		lblSkillArrow2.setHorizontalAlignment(SwingConstants.CENTER);
		panelSkills2.add(lblSkillArrow2);
		
		listSkillToExec2 = new JList<String>();
		listSkillToExec2.setBounds(120, 18, 83, 157);
		panelSkills2.add(listSkillToExec2);
		
		buttonAttack2 = new JButton("攻击");
		buttonAttack2.setBounds(205, 60, 75, 29);
		panelSkills2.add(buttonAttack2);
		
		JButton buttonCancel2 = new JButton("撤销");
		buttonCancel2.setBounds(205, 90, 75, 29);
		panelSkills2.add(buttonCancel2);
		
		panelCommanderInfo2 = new JPanel();
		panelCommanderInfo2.setBorder(new TitledBorder(null, "\u53EC\u5524\u5E08\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommander2.add(panelCommanderInfo2);
		panelCommanderInfo2.setLayout(null);
		
		lblCommanderName2 = new JLabel("召唤师名");
		lblCommanderName2.setBounds(104, 25, 79, 35);
		panelCommanderInfo2.add(lblCommanderName2);
		
		progressHealth2 = new JProgressBar();
		progressHealth2.setValue(50);
		progressHealth2.setBounds(60, 69, 146, 20);
		panelCommanderInfo2.add(progressHealth2);
		
		JLabel lblHealth2 = new JLabel("生命值");
		lblHealth2.setBounds(10, 71, 46, 16);
		panelCommanderInfo2.add(lblHealth2);
		
		progressEnergy2 = new JProgressBar();
		progressEnergy2.setBounds(60, 101, 146, 20);
		panelCommanderInfo2.add(progressEnergy2);
		
		JLabel lblEnergy2 = new JLabel("能量值");
		lblEnergy2.setBounds(10, 103, 46, 16);
		panelCommanderInfo2.add(lblEnergy2);
		
		lblHealthTip2 = new JLabel("1/100");
		lblHealthTip2.setBounds(210, 71, 65, 16);
		panelCommanderInfo2.add(lblHealthTip2);
		
		lblEnergyTip2 = new JLabel("1/100");
		lblEnergyTip2.setBounds(210, 103, 65, 16);
		panelCommanderInfo2.add(lblEnergyTip2);
		
		lblEnergy1.setVisible(false);
		lblEnergy2.setVisible(false);
		progressEnergy1.setVisible(false);
		progressEnergy2.setVisible(false);
		lblEnergyTip1.setVisible(false);
		lblEnergyTip2.setVisible(false);
		
		setResizable(false);
		
		bindData();
		
		// 状态控制
		curState = new FirstPartnerOperationState(this);
		curState.update();
		
		// 延后事件绑定
		SkillReadyHandler readyHandler1 = new SkillReadyHandler(thatStageService, listSkillReady1, listSkillToExec1);
		readyHandler1.commanderIndex = 0;
		readyHandler1.skillService = skillServiceForFir;
		listSkillReady1.addMouseListener(readyHandler1);
		
		SkillReadyHandler skillHandler2 = new SkillReadyHandler(thatStageService, listSkillReady2, listSkillToExec2);
		skillHandler2.skillService = skillServiceForSec;
		skillHandler2.commanderIndex = 1;
		listSkillReady2.addMouseListener(skillHandler2);
		
		CancelSkillHandler cancelSkillHandler1 = new CancelSkillHandler();
		CommanderPartnerCancel firstPartnerCancel = new CommanderPartnerCancel();
		cancelSkillHandler1.setCancel(firstPartnerCancel);
		firstPartnerCancel.setSkillService(skillServiceForFir);
		firstPartnerCancel.setContext(listSkillToExec1);
		buttonCancel1.addMouseListener(cancelSkillHandler1);
		
		CommanderPartnerCancel secondPartnerCancel = new CommanderPartnerCancel();
		secondPartnerCancel.setSkillService(skillServiceForSec);
		secondPartnerCancel.setContext(listSkillToExec2);
		CancelSkillHandler cancelSkillHandler2 = new CancelSkillHandler();
		cancelSkillHandler2.setCancel(secondPartnerCancel);
		buttonCancel2.addMouseListener(cancelSkillHandler2);
		
		AttackHandler attackHandler2 = new AttackHandler();
		attackHandler2.setContext(this);
		attackHandler2.setIndex(2);
		attackHandler2.setSkillService(skillServiceForSec);
		buttonAttack2.addMouseListener(attackHandler2);
		
		AttackHandler attackHandler1 = new AttackHandler();
		attackHandler1.setContext(this);
		attackHandler1.setSkillService(skillServiceForFir);
		attackHandler1.setIndex(1);
		buttonAttack1.addMouseListener(attackHandler1);
		
		// 添加菜单项
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("角色");
		menuBar.add(menu);
		JMenuItem reAlive = new JMenuItem("复活");
		menu.add(reAlive);
		String os = System.getProperty("os.name");  
		if (os.toLowerCase().contains("mac")) {
			System.setProperty("apple.laf.useScreenMenuBar","true");//设置程序的menubar显示到Mac的菜单栏上
		}
		final FightStageFrame thatWindow = this;
		reAlive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 访问备忘录信息，以复活
//				FightStageFrame.this.stageService.
				thatStageService.reviveAllCommander();
				JOptionPane.showMessageDialog(thatWindow, "所有召唤师已复活，你可以继续战斗", "复活提醒", JOptionPane.INFORMATION_MESSAGE);
				// 更新状态
				curState = new FirstPartnerOperationState(thatWindow);
				curState.update();
				// 重新绑定数据
				thatWindow.bindData();
				thatWindow.revalidate();
				thatWindow.repaint();
			}
		});;
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
	
	private void bindData() {
		
		// 获取背包信息
		List<String> equipments = stageService.getEquipments(0);
		panelBagEquipments1.removeAll();
		panelBagEquipments2.removeAll();
		for (String equipName : equipments) {
			JLabel lblEquip = new JLabel(equipName);
			lblEquip.setHorizontalAlignment(SwingConstants.CENTER);
			lblEquip.setHorizontalTextPosition(SwingConstants.CENTER);
			lblEquip.setVerticalAlignment(SwingConstants.CENTER);
			lblEquip.setVerticalTextPosition(SwingConstants.CENTER);
			panelBagEquipments1.add(lblEquip);
		}
		
		equipments = stageService.getEquipments(1);
		for (String equipName : equipments) {
			JLabel lblEquip = new JLabel(equipName);
			lblEquip.setHorizontalAlignment(SwingConstants.CENTER);
			lblEquip.setHorizontalTextPosition(SwingConstants.CENTER);
			lblEquip.setVerticalAlignment(SwingConstants.CENTER);
			lblEquip.setVerticalTextPosition(SwingConstants.CENTER);
			panelBagEquipments2.add(lblEquip);
		}
		
		// 获取技能信息
		List<SkillVO> skills1 = stageService.getSkills(0);
		DefaultListModel<String> listModel1 = new DefaultListModel<String>();
		for (SkillVO skillVo : skills1) {
			String skillName = skillVo.getName();
			int level = skillVo.getLevel();
			listModel1.addElement(skillName + "(" + level + "级)");
		}
		listSkillReady1.setModel(listModel1);
		skills1 = stageService.getSkills(1);
		
		List<SkillVO> skills2 = stageService.getSkills(1);
		DefaultListModel<String> listModel2 = new DefaultListModel<String>();
		for (SkillVO skillVo : skills2) {
			String skillName = skillVo.getName();
			int level = skillVo.getLevel();
			listModel2.addElement(skillName + "(" + level + "级)");
		}
		listSkillReady2.setModel(listModel2);
		
		// 为结果技能添加默认模型
		listSkillToExec1.setModel(new DefaultListModel<String>());
		listSkillToExec2.setModel(new DefaultListModel<String>());
		
		// 召唤师信息
		CommanderBasicVO commanderInfoFir = stageService.getCommanderInfo(0);
		progressHealth1.setMaximum(commanderInfoFir.getHealth());
		progressHealth1.setValue(stageService.getCurrentHealth(commanderInfoFir.getName()));
		lblCommanderName1.setText(commanderInfoFir.getName());
		lblHealthTip1.setText(stageService.getCurrentHealth(commanderInfoFir.getName()) + "/" + commanderInfoFir.getHealth());
//		progressEnergy1.setMaximum(commanderInfoFir.get);
		
		CommanderBasicVO commanderInfoSec = stageService.getCommanderInfo(1);
		progressHealth2.setMaximum(commanderInfoSec.getHealth());
		progressHealth2.setValue(stageService.getCurrentHealth(commanderInfoSec.getName()));
		lblHealthTip2.setText(stageService.getCurrentHealth(commanderInfoSec.getName()) + "/" + commanderInfoSec.getHealth());
		lblCommanderName2.setText(commanderInfoSec.getName());
		
		stageService.registerPartnerObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (stageService.isFirstPartner(o)) {
			// 判断是否目标死亡
			if (o instanceof Commander) {
				if (!((Target) o).isAlive()) {
					curState.toGameOverState();
					curState.update();
					stageService.collectExprience(stageService.getCommanderInfo(1).getName(), (Target) o);
				}
			}
			// 更新可变属性
			progressHealth1.setValue(stageService.getHealth(o));
			progressEnergy1.setValue(stageService.getEnergy(o));
			lblHealthTip1.setText(stageService.getCurrentHealth(((Target) o).getName()) + "/" + progressHealth1.getMaximum());
			progressHealth1.revalidate();
			progressHealth1.repaint();
			progressEnergy1.revalidate();
			progressEnergy1.repaint();
		} else if (stageService.isSecondPartner(o)) {
			progressEnergy2.setValue(stageService.getEnergy(o));
			progressHealth2.setValue(stageService.getHealth(o));
			lblHealthTip2.setText(stageService.getCurrentHealth(((Target) o).getName()) + "/" + progressHealth2.getMaximum());
			progressHealth2.revalidate();
			progressHealth2.repaint();
			progressEnergy2.revalidate();
			progressEnergy2.repaint();
			if (o instanceof Commander) {
				if (!((Target) o).isAlive()) {
					curState.toGameOverState();
					curState.update();
					stageService.collectExprience(stageService.getCommanderInfo(0).getName(), (Target) o);
				}
			}
		} else if (stageService.isMonster(o)) {
			// 将参与者移除
			stageService.clearDied();
			// 由于是共同造成伤害，因此经验共享
			if (o instanceof Target) {
				if (!((Target) o).isAlive()) {
					stageService.collectExprience(stageService.getCommanderInfo(0).getName(), (Target) o);
					stageService.collectExprience(stageService.getCommanderInfo(1).getName(), (Target) o);
				}
			}
		}
	}

	/**
	 * @return the skillServiceForFir
	 */
	public SkillService getSkillServiceForFir() {
		return skillServiceForFir;
	}

	/**
	 * @param skillServiceForFir the skillServiceForFir to set
	 */
	public void setSkillServiceForFir(SkillService skillServiceForFir) {
		this.skillServiceForFir = skillServiceForFir;
	}

	/**
	 * @return the skillServiceForSec
	 */
	public SkillService getSkillServiceForSec() {
		return skillServiceForSec;
	}

	/**
	 * @param skillServiceForSec the skillServiceForSec to set
	 */
	public void setSkillServiceForSec(SkillService skillServiceForSec) {
		this.skillServiceForSec = skillServiceForSec;
	}

	/**
	 * @return the curState
	 */
	public FightState getCurState() {
		return curState;
	}

	/**
	 * @param curState the curState to set
	 */
	public void setCurState(FightState curState) {
		this.curState = curState;
	}
	
	public void nextState() {
		curState.next();
		curState.update();
		if (curState instanceof MonsterOperationState) {
			stageService.attack(-1, null);
			nextState();
		}
	}
}
