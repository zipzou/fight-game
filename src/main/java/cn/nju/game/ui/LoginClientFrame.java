package cn.nju.game.ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.apple.eawt.Application;

import cn.nju.game.ui.handler.CreateRoleActionHandler;
import cn.nju.game.ui.handler.LoginActionHandler;
import cn.nju.game.ui.util.BoundsUtil;

public class LoginClientFrame {

	private JFrame frame;
	private JTextField txtCommanderName1;
	private JTextField txtCommanderName2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginClientFrame window = new LoginClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginClientFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String os = System.getProperty("os.name");  
		if (os.toLowerCase().contains("mac")) {
			System.setProperty("apple.laf.useScreenMenuBar","true");//设置程序的menubar显示到Mac的菜单栏上
		}
		frame = new JFrame();
		int w = 400;
		int h = 200;
		frame.setBounds(BoundsUtil.getCenterOwnerBounds(w, h));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar jMenuBar = new JMenuBar();
	    frame.setJMenuBar(jMenuBar);
	    JMenu jMenu = new JMenu("角色");
//	    JMenuItem item2 = new JMenuItem("item2");
//	    JMenuItem item3 = new JMenuItem("item3");
//	    JMenuItem item4 = new JMenuItem("退出");
	    JMenuItem createRoleMenuItem = new JMenuItem("新建");
		final JFrame tmpFrame = frame;
		
		CreateRoleActionHandler createRoleActionHandler = new CreateRoleActionHandler();
		createRoleActionHandler.setOwnerFrame(tmpFrame);
	    createRoleMenuItem.addActionListener(createRoleActionHandler);
	    
	    jMenu.add(createRoleMenuItem);
//	    jMenu.add(item2);
//	    jMenu.add(item3);
//	    jMenu.add(item4);
	    jMenuBar.add(jMenu);
	    frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
	    
	    JPanel leftPanel = new JPanel();
	    leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
	    frame.getContentPane().add(leftPanel);
	    leftPanel.setLayout(new GridLayout(4, 1, 0, 0));
	    LoginActionHandler loginActionHandler = new LoginActionHandler();
	    
	    JPanel panelName1 = new JPanel();
	    leftPanel.add(panelName1);
	    
	    JLabel labelCommanderName1 = new JLabel("召唤师名：");
	    panelName1.add(labelCommanderName1);
	    
	    txtCommanderName1 = new JTextField();
	    panelName1.add(txtCommanderName1);
	    txtCommanderName1.setColumns(10);
	    
	    JPanel panelName2 = new JPanel();
	    panelName2.setVisible(false);
	    leftPanel.add(panelName2);
	    
	    JLabel labelCommanderName2 = new JLabel("召唤师名2：");
	    panelName2.add(labelCommanderName2);
	    
	    txtCommanderName2 = new JTextField();
	    txtCommanderName2.setColumns(10);
	    panelName2.add(txtCommanderName2);
	    
	    JPanel panelGameMode = new JPanel();
	    leftPanel.add(panelGameMode);
	    
	    JRadioButton radioSingleGame = new JRadioButton("单人游戏");
	    radioSingleGame.setSelected(true);
	    buttonGroup.add(radioSingleGame);
	    panelGameMode.add(radioSingleGame);
	    
	    JRadioButton radioMultiGame = new JRadioButton("多人游戏");
	    buttonGroup.add(radioMultiGame);
	    panelGameMode.add(radioMultiGame);
	    panelGameMode.setVisible(false);
	    
	    final JRadioButton radio1 = radioMultiGame, radio2 = radioSingleGame;
	    final JPanel panelMode2 = panelName2;
	    final LoginActionHandler loginActionHandlerFinal = loginActionHandler;
	    ActionListener gameMode = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (radio1.isSelected()) {
	    			panelMode2.setVisible(true);
	    			loginActionHandlerFinal.setLoginMode(2);
	    		} else if (radio2.isSelected()) {
	    			panelMode2.setVisible(false);
	    			loginActionHandlerFinal.setLoginMode(1);
	    		}
	    	}
	    };
	    radioMultiGame.addActionListener(gameMode);
	    radioSingleGame.addActionListener(gameMode);
	    loginActionHandler.setCommanderNameText1(txtCommanderName1);
	    loginActionHandler.setCommanderNameText2(txtCommanderName2);
	    
	    JPanel panelButton = new JPanel();
	    leftPanel.add(panelButton);
	    
	    JButton button = new JButton("进入");
	    panelButton.add(button);
	    button.addMouseListener(loginActionHandler);
	    loginActionHandler.setParent(tmpFrame);
	    frame.setResizable(false);
	    
	    String OsName = System.getProperty("os.name");
      //是mac 就设置dock图标
      if (OsName.toLowerCase().contains("mac")) {
          Image icon_image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
          //指定mac 的dock图标
          Application app = Application.getApplication();
          app.setDockIconImage(icon_image);
//          PopupMenu popupMenu = new PopupMenu();
//          MenuItem menuitem1 = new MenuItem("1");
//          MenuItem menuitem2 = new MenuItem("2");
//          MenuItem menuitem3 = new MenuItem("3");
//          popupMenu.add(menuitem1);
//          popupMenu.add(menuitem2);
//          popupMenu.add(menuitem3);
//          app.setDockMenu(popupMenu);
//          app.setQuitStrategy(QuitStrategy.CLOSE_ALL_WINDOWS);
      }
	    
//		//获得操作系统
//        String OsName = System.getProperty("os.name");
//        //是mac 就设置dock图标
//        if (OsName.contains("Mac")) {
//            Image icon_image = Toolkit.getDefaultToolkit().getImage("icon.png");
//            //指定mac 的dock图标
//            Application app = Application.getApplication();
//            app.setDockIconImage(icon_image);
//        }
	    
	}

}
