/**
 * 
 */
package cn.nju.game.ui.handler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.role.Commander;
import cn.nju.game.service.OnlineCommander;
import cn.nju.game.util.ObjectiveSerializeUtil;

/**
 * 窗口即将关闭的处理事件
 * @author frank
 *
 */
public class WindowClosingHandler extends WindowAdapter {
	/* (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		List<Commander> allOnlines = OnlineCommander.sharedCommanders().getAllOnlines();
		for (Commander commander : allOnlines) {
			try {
				ObjectiveSerializeUtil.serialize(GameConfiguration.sharedConfiguration().read(GameConfiguration.ROLES_FILE).toString(), commander.getName(), commander);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
