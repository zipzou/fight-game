package cn.nju.game.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cn.nju.game.equip.Bag;
import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.EquipmentVO;
import cn.nju.game.model.vo.SkillVO;
import cn.nju.game.role.Commander;
import cn.nju.game.role.JuniorStrategy;
import cn.nju.game.role.LevelUpStrategy;
import cn.nju.game.role.PrimaryLevelupStrategy;
import cn.nju.game.role.SeniorStrategy;
import cn.nju.game.service.OnlineCommander;
import cn.nju.game.service.RoleService;
import cn.nju.game.service.StageService;
import cn.nju.game.skill.DeleteEquipItemListener;
import cn.nju.game.skill.SkillLeveledPool;
import cn.nju.game.ui.handler.SkillUpgradeListener;
import cn.nju.game.ui.handler.StartFightMouseHandler;
import cn.nju.game.ui.handler.StrengthWeaponLeadingListener;
import cn.nju.game.ui.handler.StrengthWeaponTailListener;
import cn.nju.game.ui.handler.WindowClosingHandler;
import cn.nju.game.ui.util.BoundsUtil;
import cn.nju.game.ui.util.CommanderModelUtilFactory;
import cn.nju.game.ui.util.EquipmentModelUtilFactory;
import cn.nju.game.ui.util.ModelToTableModelUtil;
import cn.nju.game.ui.util.SharedModelUtilFactory;
import cn.nju.game.ui.util.SkillModelUtilFactory;

public class GameClientWindow extends JFrame implements Observer {

	private static final String EXP_TOOLTIP = "经验值/升级所需经验";
	private static final int W = 800;
	private static final int H = 600;
	private RoleService roleService;
	private StageService stageService;
	
	private List<String> equipmentsInBag = new ArrayList<String>(Bag.CAPACITY);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646291614420411539L;
	private JPanel contentPane;
	private JTable tableEquipments;
	private JTable tableSkills;
	private JTable tableOnlineCommanders;
	private JPanel[] rowPanels;
	private JButton buttonAddToBag;
	private JLabel lblCommanderName;
	private JLabel lblExprience;
	private JLabel lblLevel;
	private LevelUpStrategy strategy;

	public void showInCenter(JFrame parent) {
		setBounds(BoundsUtil.getCenterOwnerBounds(parent, W, H));
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GameClientWindow(RoleService service, StageService stageService) {
		this.stageService = stageService;
		setResizable(false);
		roleService = service;
		CommanderBasicVO commanderBasic = roleService.getCommanderBasic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, W, H);
		setMaximumSize(new Dimension(W, H));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel lblCommanderInfo = new JPanel();
		lblCommanderInfo.setBorder(new TitledBorder(null, "召唤师信息", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lblCommanderInfo.setBounds(6, 6, 230, 250);
		contentPane.add(lblCommanderInfo);
		
		JLabel lblIcon = new JLabel();
		lblIcon.setBounds(6, 18, 100, 128);
		try {
			ImageIcon imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/" + commanderBasic.getIcon())));
			lblIcon.setIcon(imageIcon);
			int magicDamage = commanderBasic.getMagicDamage();
			int physicalDamage = commanderBasic.getPhysicalDamage();
			magicDamage = (int) (magicDamage * (1 + Math.log10(commanderBasic.getLevel())));
			physicalDamage = (int) (physicalDamage * (1 + Math.log10(commanderBasic.getLevel())));
			lblIcon.setToolTipText("将对目标造成" + physicalDamage + "的物理伤害和" + magicDamage + "的魔法伤害，基础伤害将随等级自适应提高");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblCommanderInfo.setLayout(null);
		lblCommanderInfo.add(lblIcon);
		
		lblCommanderName = new JLabel(commanderBasic.getName());
		lblCommanderName.setBounds(113, 18, 114, 16);
		lblCommanderInfo.add(lblCommanderName);
		
		JLabel lblJob = new JLabel(commanderBasic.getJob());
		lblJob.setBounds(113, 36, 114, 16);
		lblCommanderInfo.add(lblJob);
		
		lblLevel = new JLabel(commanderBasic.getLevel() + "");
		lblLevel.setBounds(113, 54, 114, 16);
		lblCommanderInfo.add(lblLevel);
		
		strategy = null;
		if (4 > commanderBasic.getLevel()) {
			strategy = new PrimaryLevelupStrategy();
		} else if (10 >= commanderBasic.getLevel()) {
			strategy = new JuniorStrategy();
		} else {
			strategy = new SeniorStrategy();
		}
		
		lblExprience = new JLabel(commanderBasic.getExpirience() + "/" + strategy.getLevelupExperienceNeeded(commanderBasic.getLevel()));
		lblExprience.setBounds(113, 72, 114, 16);
		lblExprience.setToolTipText(EXP_TOOLTIP);
		lblCommanderInfo.add(lblExprience);
		JLabel lblWeapon = new JLabel(roleService.getWeaponVo().getName());
		lblWeapon.setToolTipText(roleService.getWeaponVo().getDescription());
		lblWeapon.setBounds(113, 90, 114, 16);
		lblCommanderInfo.add(lblWeapon);
		
		JButton buttonStart = new JButton("确认并开始竞技");
		StartFightMouseHandler startFightMouseHandler = new StartFightMouseHandler();
		buttonStart.addMouseListener(startFightMouseHandler);
		startFightMouseHandler.setContext(this);
		startFightMouseHandler.setStageService(stageService);
		buttonStart.setBounds(59, 199, 117, 29);
		lblCommanderInfo.add(buttonStart);
		
		JButton buttonStrengthLeading = new JButton("加强武器头部");
		buttonStrengthLeading.setBounds(110, 110, 117, 29);
		StrengthWeaponLeadingListener strengthWeaponLeadingListener = new StrengthWeaponLeadingListener();
		strengthWeaponLeadingListener.setObservrLabel(lblWeapon);
		strengthWeaponLeadingListener.setRoleService(roleService);
		buttonStrengthLeading.addMouseListener(strengthWeaponLeadingListener);
		lblCommanderInfo.add(buttonStrengthLeading);
		
		JButton buttonStrengthTail = new JButton("加强武器尾部");
		buttonStrengthTail.setBounds(110, 136, 117, 29);
		StrengthWeaponTailListener strengthWeaponTailListener = new StrengthWeaponTailListener();
		strengthWeaponTailListener.setObservrLabel(lblWeapon);
		strengthWeaponTailListener.setRoleService(roleService);
		buttonStrengthTail.addMouseListener(strengthWeaponTailListener);
		lblCommanderInfo.add(buttonStrengthTail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 6, 546, 250);
		contentPane.add(scrollPane);
		
		JPanel panelEquipments = new JPanel();
		scrollPane.setViewportView(panelEquipments);
		scrollPane.setBorder(null);
		panelEquipments.setBorder(new TitledBorder(null, "装备库", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelEquipments.setLayout(new BorderLayout(0, 0));
		
		Iterable<Equipment> allEquipments = EquipmentShop.sharedPool().getAllEquipments();
		Set<EquipmentVO> allEquipmentsVo = new HashSet<EquipmentVO>();
		for (Equipment equipment : allEquipments) {
			allEquipmentsVo.add(new EquipmentVO(equipment));
		}
		Iterator<EquipmentVO> equipIt = allEquipmentsVo.iterator();
		Object[] headers = null;
		Vector<Object[]> equipVals = new Vector<Object[]>();
		ModelToTableModelUtil util = null;
		try {
			util = SharedModelUtilFactory.sharedFactory().getUtil(EquipmentModelUtilFactory.class.getName());
		} catch (InstantiationException e3) {
			e3.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
		if (equipIt.hasNext()) {
			EquipmentVO fir = equipIt.next();
			try {
				headers = util.transferModelToHeader(fir);
				equipVals.add(util.transferModelToValues(fir));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		while (equipIt.hasNext()) {
			try {
				equipVals.add(util.transferModelToValues(equipIt.next()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tableEquipments = new JTable();
		tableEquipments.addMouseListener(new MouseAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				int rowIndex = tableEquipments.rowAtPoint(e.getPoint());
				tableEquipments.setRowSelectionInterval(rowIndex, rowIndex);
				if (2 <= clickCount) {
					buttonAddToBag.doClick();
				}
			}
		});
		Object[][] rows = new Object[equipVals.size()][];
		int i = 0;
		for (Object[] objects : equipVals) {
			rows[i] = objects;
			i++;
		}
		if (null != headers) {
			TableModel dataModel = new DefaultTableModel(rows, headers) {
				private static final long serialVersionUID = 5597352217509676902L;
				public boolean isCellEditable(int row, int column) {return false;};
			};
			tableEquipments.setModel(dataModel);
		}
		tableEquipments.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panelEquipments.add(tableEquipments, BorderLayout.CENTER);
		panelEquipments.add(tableEquipments.getTableHeader(), BorderLayout.NORTH);
		
		buttonAddToBag = new JButton("添加到背包");
		final JFrame that = this;
		buttonAddToBag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = tableEquipments.getSelectedRows();
				if (Bag.CAPACITY < equipmentsInBag.size() + selectedRows.length) {
					JOptionPane.showMessageDialog(that, "你的背包已满，无法再添加额外的" + selectedRows.length + "件装备", "背包已满", JOptionPane.PLAIN_MESSAGE);
				} else {
					for (int rowIndex : selectedRows) {
						Object equip = ((DefaultTableModel) tableEquipments.getModel()).getValueAt(rowIndex, 0);
						equipmentsInBag.add(equip.toString());
					}
					paintEquipments();
				}
			}

			/**
			 * 绘制选中的行
			 * @param selectedRows
			 */
			private void paintEquipments() {
				for (JPanel jPanel : rowPanels) {
					jPanel.removeAll();
					jPanel.revalidate();
					jPanel.repaint();
				}
				for (int i = 0; i < equipmentsInBag.size(); i++) {
					int rowI = i / 3;
					int colI = i % 3;
					JPanel rowPanel = rowPanels[rowI];
					JLabel lblEquip = new JLabel(equipmentsInBag.get(i).toString());
					JPopupMenu rightMenu = new JPopupMenu();
					DeleteEquipItemListener equipItemDeleteListener = new DeleteEquipItemListener();
					JMenuItem deleteItem = new JMenuItem("删除");
					equipItemDeleteListener.setDataIndex(i);
					equipItemDeleteListener.setComponentIndex(colI);
					equipItemDeleteListener.setParent(rowPanel);
					equipItemDeleteListener.setData(equipmentsInBag);
					equipItemDeleteListener.setCallback(new Runnable() {
						public void run() {
							paintEquipments();
						}
					});
					deleteItem.addActionListener(equipItemDeleteListener);
					rightMenu.add(deleteItem);
					lblEquip.setComponentPopupMenu(rightMenu);
					rowPanel.add(lblEquip);
					rowPanel.revalidate();
					rowPanel.repaint();
				}
			}
		});
		panelEquipments.add(buttonAddToBag, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneSkills = new JScrollPane();
		scrollPaneSkills.setBounds(248, 268, 269, 304);
		contentPane.add(scrollPaneSkills);
		
		JPanel panelSkills = new JPanel();
		panelSkills.setBorder(new TitledBorder(null, "技能库", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPaneSkills.setViewportView(panelSkills);
		scrollPaneSkills.setBorder(null);
		panelSkills.setLayout(new BorderLayout(0, 0));
		
		tableSkills = new JTable();

		JPopupMenu tableSkillMenu = createSkillUpgradeMenu();
		final JPopupMenu tableSkillMenuFinal = tableSkillMenu;
		tableSkills.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					//通过点击位置找到点击为表格中的行
		            int focusedRowIndex = tableSkills.rowAtPoint(e.getPoint());
		            if (focusedRowIndex == -1) {
		                return;
		            }
		            tableSkills.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
		            tableSkillMenuFinal.show(tableSkills, e.getX(), e.getY());
				}
			}
		});
		panelSkills.add(tableSkills, BorderLayout.CENTER);
		
		// 加载所有技能
		List<String> allSkills = SkillLeveledPool.sharedPool().getAllSkills();
		List<SkillVO> skillsVo = new ArrayList<SkillVO>();
		for (String skillName : allSkills) {
			skillsVo.add(new SkillVO(SkillLeveledPool.sharedPool().getSkill(skillName, 1)));
		}
		Iterator<SkillVO> skillIt = skillsVo.iterator();
		ModelToTableModelUtil skillModelUtil = null;
		try {
			skillModelUtil = SharedModelUtilFactory.sharedFactory().getUtil(SkillModelUtilFactory.class.getName());
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		headers = null;
		Vector<Object[]> skillVals = new Vector<Object[]>();
		if (skillIt.hasNext()) {
			SkillVO skillVo = skillIt.next();
			try {
				headers = skillModelUtil.transferModelToHeader(skillVo);
				skillVals.add(skillModelUtil.transferModelToValues(skillVo));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		while (skillIt.hasNext()) {
			SkillVO skillVO = skillIt.next();
			try {
				skillVals.add(skillModelUtil.transferModelToValues(skillVO));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		rows = new Object[skillVals.size()][];
		for (int j = 0; j < rows.length; j++) {
			rows[j] = skillVals.get(j);
		}

		tableSkills.setModel(new DefaultTableModel(rows, headers) {
			private static final long serialVersionUID = -643106778033014822L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		panelSkills.add(tableSkills.getTableHeader(), BorderLayout.NORTH);
		
		JPanel panelBag = new JPanel();
		panelBag.setBorder(new TitledBorder(null, "我的背包", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelBag.setBounds(6, 268, 230, 304);
		contentPane.add(panelBag);
		panelBag.setLayout(new GridLayout(3, 3, 5, 5));
		
		JPanel panelRow1 = new JPanel();
		panelRow1.setBorder(null);
		panelBag.add(panelRow1);
		panelRow1.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		JPanel panelRow2 = new JPanel();
		panelRow2.setBorder(null);
		panelBag.add(panelRow2);
		
		JPanel panelRow3 = new JPanel();
		panelBag.add(panelRow3);
		panelRow2.setBorder(null);
		
		rowPanels = new JPanel[3];
		rowPanels[0] = panelRow1;
		
		rowPanels[1] = panelRow2;
		panelRow2.setLayout(new GridLayout(0, 3, 0, 0));
		rowPanels[2] = panelRow3;
		panelRow3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JScrollPane scrollOnlineCommanders = new JScrollPane();
		scrollOnlineCommanders.setBorder(null);
		scrollOnlineCommanders.setBounds(529, 268, 265, 304);
		contentPane.add(scrollOnlineCommanders);
		
		JPanel panelOnlineCommanderTableContainer = new JPanel();
		panelOnlineCommanderTableContainer.setBorder(new TitledBorder(null, "在线召唤师", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollOnlineCommanders.setViewportView(panelOnlineCommanderTableContainer);
		panelOnlineCommanderTableContainer.setLayout(new BorderLayout(0, 0));
		
		tableOnlineCommanders = new JTable();
		tableOnlineCommanders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelOnlineCommanderTableContainer.add(tableOnlineCommanders);
		panelOnlineCommanderTableContainer.add(tableOnlineCommanders.getTableHeader(), BorderLayout.NORTH);
		startFightMouseHandler.setTableCommander(tableOnlineCommanders);
		startFightMouseHandler.setTableSkills(tableSkills);
		
		// 在线召唤师获取
		List<Commander> allCommanders = OnlineCommander.sharedCommanders().getAllOnlines();
		List<CommanderBasicVO> allCommandersVo = new ArrayList<CommanderBasicVO>();
		for (Commander commander : allCommanders) {
			allCommandersVo.add(commander.getBasicVO());
		}
		
		ModelToTableModelUtil commanderUtil = null;
		try {
			commanderUtil = SharedModelUtilFactory.sharedFactory().getUtil(CommanderModelUtilFactory.class.getName());
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Iterator<CommanderBasicVO> commanderIt = allCommandersVo.iterator();
		Vector<Object[]> commanderVals = new Vector<Object[]>();
		headers = null;
		if (commanderIt.hasNext()) {
			CommanderBasicVO commanderVo = commanderIt.next();
			try {
				headers = commanderUtil.transferModelToHeader(commanderVo);
				commanderVals.add(commanderUtil.transferModelToValues(commanderVo));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		while (commanderIt.hasNext()) {
			try {
				commanderVals.add(commanderUtil.transferModelToValues(commanderIt.next()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		rows = new Object[commanderVals.size()][];
		for(int j = 0; j < rows.length; j++) {
			rows[j] = commanderVals.get(j);
		}
		
		tableOnlineCommanders.setModel(new DefaultTableModel(rows, headers) {
			private static final long serialVersionUID = 9115983158408713371L;
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		});
		
		// 设置开始处理类参数
		startFightMouseHandler.setEquipments(equipmentsInBag);
		
		// 窗口关闭事件处理
		addWindowListener(new WindowClosingHandler());
	}

	/**
	 * @return
	 */
	private JPopupMenu createSkillUpgradeMenu() {
		JPopupMenu tableSkillMenu = new JPopupMenu();
		JMenuItem upgradeMenuItem = new JMenuItem("升级");
		tableSkillMenu.add(upgradeMenuItem);
		SkillUpgradeListener skillUpgradeListener = new SkillUpgradeListener();
		skillUpgradeListener.setContext(tableSkills);
		upgradeMenuItem.addActionListener(skillUpgradeListener);
		return tableSkillMenu;
	}

	/**
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
	 * @return the lblCommanderName
	 */
	public JLabel getLblCommanderName() {
		return lblCommanderName;
	}

	/**
	 * @param lblCommanderName the lblCommanderName to set
	 */
	public void setLblCommanderName(JLabel lblCommanderName) {
		this.lblCommanderName = lblCommanderName;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
//		int currentExprience = roleService.getCurrentExprience((Commander) arg);
		CommanderBasicVO commanderBasic = roleService.getCommanderBasic();
		if (commanderBasic.getName().equals(((Commander) arg).getName())) {
			String tip = commanderBasic.getExpirience() + "/" + strategy.getLevelupExperienceNeeded(commanderBasic.getLevel());
			lblExprience.setText(tip);
			lblLevel.setText(commanderBasic.getLevel() + "");
		}
	}
}
