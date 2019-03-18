/**
 * 
 */
package cn.nju.game.ui.util;

import java.awt.Component;
import java.awt.Container;

/**
 * 组件启用或禁用工具
 * @author frank
 *
 */
public class ComponentSwitchUtil {
	/**
	 * 禁用组件
	 * @param component 父组件或容器
	 */
	public static void disableUi(Component component) {
		component.setEnabled(false);
		if (component instanceof Container) {
			Component[] subs = ((Container) component).getComponents();
			for (Component subComponent : subs) {
				disableUi(subComponent);
			}
		}
	}
	
	/**
	 * 启用组件
	 * @param component 父组件或容器
	 */
	public static void enableUi(Component component) {
		component.setEnabled(true);
		if (component instanceof Container) {
			Component[] subs = ((Container) component).getComponents();
			for (Component subComponent : subs) {
				enableUi(subComponent);
			}
		}
	}
}
