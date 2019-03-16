/**
 * 
 */
package cn.nju.game.ui.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型转换工具工厂
 * @author frank
 *
 */
public class SharedModelUtilFactory {

	/**
	 * 对象映射缓存
	 */
	private Map<String, ModelToTableModelUtil> utilCaches;

	protected SharedModelUtilFactory() {
		super();
		utilCaches = new HashMap<String, ModelToTableModelUtil>();
	}
	
	private static final class SharedModelUtilFactoryHolder {
		private static final SharedModelUtilFactory _INSTANCE = new SharedModelUtilFactory();
	}
	
	/**
	 * 获取单例工厂
	 * @return 单例工厂实例
	 */
	public static SharedModelUtilFactory sharedFactory() {
		return SharedModelUtilFactoryHolder._INSTANCE;
	}
	
	/**
	 * 获取转换工具
	 * @param factory 转换工具产生的工厂
	 * @return 模型转换工具
	 * @throws ClassNotFoundException 工厂类无法加载时产生该异常，请确认工厂类指定无误
	 * @throws IllegalAccessException 工厂类构造函数无法访问时发生该异常，该方法将调用工厂类的默认构造方法，若默认构造方法无法访问，则无法创建工厂实例
	 * @throws InstantiationException 指定的类无法被实例化时发生该异常，如：指定类为抽象类、接口等
	 */
	public ModelToTableModelUtil getUtil(String factory) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!utilCaches.containsKey(factory)) {
			ModelToTableModelUtil util = ((TableModelUtilFactory) Class.forName(factory).newInstance()).createUtil();
			utilCaches.put(factory, util);
		}
		return utilCaches.get(factory);
	}
}
