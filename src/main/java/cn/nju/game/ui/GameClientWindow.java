package cn.nju.game.ui;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import cn.nju.game.equip.Equipment;
import cn.nju.game.equip.EquipmentShop;
import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.model.vo.EquipmentVO;
import cn.nju.game.service.RoleService;
import cn.nju.game.ui.util.BoundsUtil;
import cn.nju.game.ui.util.EquipmentTableModelUtil;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;

public class GameClientWindow extends JFrame {

	private static final String EXP_TOOLTIP = "经验值/升级所需经验";
	private static final int W = 800;
	private static final int H = 600;
	private RoleService roleService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646291614420411539L;
	private JPanel contentPane;
	private JTable table;

	public void showInCenter(JFrame parent) {
		setBounds(BoundsUtil.getCenterOwnerBounds(parent, W, H));
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GameClientWindow(RoleService service) {
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
		
		JLabel label = new JLabel("");
		label.setBounds(6, 18, 100, 128);
		try {
			ImageIcon imageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/" + commanderBasic.getIcon())));
			label.setIcon(imageIcon);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblCommanderInfo.setLayout(null);
		lblCommanderInfo.add(label);
		
		JLabel lblCommanderName = new JLabel(commanderBasic.getName());
		lblCommanderName.setBounds(113, 18, 114, 16);
		lblCommanderInfo.add(lblCommanderName);
		
		JLabel lblNewLabel = new JLabel(commanderBasic.getJob());
		lblNewLabel.setBounds(113, 36, 114, 16);
		lblCommanderInfo.add(lblNewLabel);
		
		JLabel lblLevel = new JLabel(commanderBasic.getLevel() + "");
		lblLevel.setBounds(113, 54, 114, 16);
		lblCommanderInfo.add(lblLevel);
		
		JLabel lblExprience = new JLabel(commanderBasic.getExpirience() + "/" + (int)((1 + Math.log10(commanderBasic.getLevel())) * 100));
		lblExprience.setBounds(113, 72, 114, 16);
		lblExprience.setToolTipText(EXP_TOOLTIP);
		lblCommanderInfo.add(lblExprience);
		JLabel lblWeapon = new JLabel(roleService.getWeaponVo().getName());
		lblWeapon.setToolTipText(roleService.getWeaponVo().getDescription());
		lblWeapon.setBounds(113, 90, 114, 16);
		lblCommanderInfo.add(lblWeapon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 6, 546, 250);
		contentPane.add(scrollPane);
		
		JPanel panelEquipments = new JPanel();
		scrollPane.setViewportView(panelEquipments);
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
		EquipmentTableModelUtil util = new EquipmentTableModelUtil(Equipment.class.getName());
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
		table = new JTable();
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
			table.setModel(dataModel);
		}
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panelEquipments.add(table, BorderLayout.CENTER);
		panelEquipments.add(table.getTableHeader(), BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		splitPane.setBounds(248, 268, 546, 304);
		contentPane.add(splitPane);
		
		JPanel panelSkills = new JPanel();
		panelSkills.setBorder(new TitledBorder(null, "\u6280\u80FD", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panelSkills);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		splitPane.setBorder(null);
		SplitPaneUI splitPaneUI = splitPane.getUI();
		if (splitPaneUI instanceof BasicSplitPaneUI) {
			((BasicSplitPaneUI) splitPaneUI).getDivider().setBorder(null);
		}
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
}
