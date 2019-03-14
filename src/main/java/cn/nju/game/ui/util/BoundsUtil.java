/**
 * 
 */
package cn.nju.game.ui.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author frank
 *
 */
public class BoundsUtil {

	/**
	 * 获取屏幕中心的Bounds
	 * @param w frame宽度
	 * @param h frame高度
	 * @return 窗口的大小及位置
	 */
	public static Rectangle getCenterOwnerBounds(int w, int h) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		int x = (screenWidth - w) / 2;
		int y = (screenHeight - h) / 2;
		return new Rectangle(x, y, w, h);
	}
	
	/**
	 * 获取基于父窗口的Bounds
	 * @param owner 父窗口
	 * @param w frame宽度
	 * @param h frame高度
	 * @return 窗口的大小及位置
	 */
	public static Rectangle getCenterOwnerBounds(JFrame owner, int w, int h) {
		Rectangle parentBounds = owner.getBounds();
		double x = parentBounds.getCenterX() - w / 2.0;
		double y = parentBounds.getCenterY() - h / 2.0;
//		setLocation((int)x, (int)y);
		return new Rectangle((int)x, (int)y, w, h);
	}
}
