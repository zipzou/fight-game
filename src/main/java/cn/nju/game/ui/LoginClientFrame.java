package cn.nju.game.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.nju.game.ui.handler.CreateRoleActionHandler;
import cn.nju.game.ui.handler.LoginActionHandler;
import cn.nju.game.ui.util.BoundsUtil;

public class LoginClientFrame {

	private JFrame frame;
	private JTextField commanderNameTextfield;
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
	    JMenuItem item2 = new JMenuItem("item2");
	    JMenuItem item3 = new JMenuItem("item3");
	    JMenuItem item4 = new JMenuItem("退出");
	    JMenuItem createRoleMenuItem = new JMenuItem("新建");
		final JFrame tmpFrame = frame;
		
		CreateRoleActionHandler createRoleActionHandler = new CreateRoleActionHandler();
		createRoleActionHandler.setOwnerFrame(tmpFrame);
	    createRoleMenuItem.addActionListener(createRoleActionHandler);
	    
	    jMenu.add(createRoleMenuItem);
	    jMenu.add(item2);
	    jMenu.add(item3);
	    jMenu.add(item4);
	    jMenuBar.add(jMenu);
	    frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
	    
	    JPanel leftPanel = new JPanel();
	    leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
	    frame.getContentPane().add(leftPanel);
	    leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    JLabel label = new JLabel("召唤师名：");
	    leftPanel.add(label);
	    
	    commanderNameTextfield = new JTextField();
	    leftPanel.add(commanderNameTextfield);
	    commanderNameTextfield.setColumns(10);
	    
	    JButton button = new JButton("进入");
	    leftPanel.add(button);
	    LoginActionHandler loginActionHandler = new LoginActionHandler();
	    loginActionHandler.setCommanderNameText(commanderNameTextfield);
	    loginActionHandler.setParent(tmpFrame);
	    button.addMouseListener(loginActionHandler);
	    
	    
	    
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
