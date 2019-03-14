package cn.nju.game.ui;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cn.nju.game.model.vo.CommanderBasicVO;
import cn.nju.game.service.RoleService;
import cn.nju.game.ui.util.BoundsUtil;

public class GameClientWindow extends JFrame {

	private static final int W = 800;
	private static final int H = 600;
	private RoleService roleService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646291614420411539L;
	private JPanel contentPane;

	public void showInCenter(JFrame parent) {
		setBounds(BoundsUtil.getCenterOwnerBounds(parent, W, H));
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GameClientWindow(RoleService service) {
		roleService = service;
		CommanderBasicVO commanderBasic = roleService.getCommanderBasic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, W, H);
		setMaximumSize(new Dimension(W, H));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "召唤师信息", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 230, 250);
		contentPane.add(panel);
		
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
		panel.setLayout(null);
		panel.add(label);
		
		JLabel label_1 = new JLabel(commanderBasic.getName());
		label_1.setBounds(113, 18, 114, 16);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel(commanderBasic.getJob());
		lblNewLabel.setBounds(113, 36, 114, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(commanderBasic.getLevel() + "");
		lblNewLabel_1.setBounds(113, 54, 114, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(commanderBasic.getExpirience() + "");
		lblNewLabel_2.setBounds(113, 72, 114, 16);
		panel.add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("武器");
		lblNewLabel_3.setBounds(113, 90, 114, 16);
		panel.add(lblNewLabel_3);
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
