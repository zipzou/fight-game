package cn.nju.game.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.nju.game.role.Commander;
import cn.nju.game.service.OnlineCommander;

public class RoleCreateFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7720583493700724331L;
	private JTextField commanderNameTextfield;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public void showInCenter(JFrame parent) {
		Rectangle parentBounds = parent.getBounds();
		double x = parentBounds.getCenterX() - 450 / 2.0;
		double y = parentBounds.getCenterY() - 300 / 2.0;
//		setLocation((int)x, (int)y);
		setBounds((int)x, (int)y, 450, 300);
		setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public RoleCreateFrame() {
//		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{92, 92, 92, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JLabel label = new JLabel("召唤师名：");
		panel.add(label);
		
		commanderNameTextfield = new JTextField();
		panel.add(commanderNameTextfield);
		commanderNameTextfield.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		
		JRadioButton warriorRadio = new JRadioButton("战士");
		buttonGroup.add(warriorRadio);
		warriorRadio.setSelected(true);
		panel_1.add(warriorRadio);
		
		JRadioButton magicianRadio = new JRadioButton("法师");
		buttonGroup.add(magicianRadio);
		panel_1.add(magicianRadio);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		getContentPane().add(panel_2, gbc_panel_2);
		
		JButton button = new JButton("创建角色");
		final JRadioButton finalWarriorRadio = warriorRadio;
		final JRadioButton finalMagicianRadio = magicianRadio;
		final JFrame finalFrame = this;
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int commanderType = 0;
				if (finalWarriorRadio.isSelected()) {
					commanderType = Commander.WARRIOR_COMMANDER;
				} else if (finalMagicianRadio.isSelected()) {
					commanderType = Commander.MAGICIAN_COMMANDER;
				}
				OnlineCommander.sharedCommanders().getPool().get(commanderNameTextfield.getText(), commanderType);
				finalFrame.setVisible(false);
			}
		});
		panel_2.add(button);
	}

}
