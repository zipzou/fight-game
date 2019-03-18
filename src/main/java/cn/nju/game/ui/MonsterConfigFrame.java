package cn.nju.game.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cn.nju.game.service.StageService;
import cn.nju.game.ui.handler.StartGameHandler;

public class MonsterConfigFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3549339334749815326L;
	private JPanel contentPane;
	private StageService stageService;
	private JSpinner spinnerMagicalMonsterCount;
	private JSpinner spinnerPhysicalMonsterCount;
	/**
	 * Create the frame.
	 */
	public MonsterConfigFrame(StageService stageService) {
		this.stageService = stageService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 10));
		
		JPanel panelPhysicalMonster = new JPanel();
		contentPane.add(panelPhysicalMonster);
		panelPhysicalMonster.setLayout(new GridLayout(0, 2, 5, 0));
		
		JLabel lblPhysicalMonster = new JLabel("物理野怪数");
		lblPhysicalMonster.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPhysicalMonster.add(lblPhysicalMonster);
		
		spinnerPhysicalMonsterCount = new JSpinner();
		panelPhysicalMonster.add(spinnerPhysicalMonsterCount);
		
		JPanel panelMagicalMonster = new JPanel();
		contentPane.add(panelMagicalMonster);
		panelMagicalMonster.setLayout(new GridLayout(0, 2, 5, 0));
		
		JLabel lblMagicalMonster = new JLabel("魔法野怪数");
		lblMagicalMonster.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMagicalMonster.setLabelFor(spinnerPhysicalMonsterCount);
		panelMagicalMonster.add(lblMagicalMonster);
		
		spinnerMagicalMonsterCount = new JSpinner();
		lblPhysicalMonster.setLabelFor(spinnerMagicalMonsterCount);
		panelMagicalMonster.add(spinnerMagicalMonsterCount);
		
		JPanel panelButton = new JPanel();
		contentPane.add(panelButton);
		
		JButton buttonStart = new JButton("确认并开始");
		StartGameHandler startGameHandler = new StartGameHandler();
		startGameHandler.setContext(this);
		buttonStart.addMouseListener(startGameHandler);
		startGameHandler.setStageService(stageService);
		panelButton.add(buttonStart);
		setResizable(false);
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
	 * @return the spinnerMagicalMonsterCount
	 */
	public JSpinner getSpinnerMagicalMonsterCount() {
		return spinnerMagicalMonsterCount;
	}
	/**
	 * @return the spinnerPhysicalMonsterCount
	 */
	public JSpinner getSpinnerPhysicalMonsterCount() {
		return spinnerPhysicalMonsterCount;
	}
}
