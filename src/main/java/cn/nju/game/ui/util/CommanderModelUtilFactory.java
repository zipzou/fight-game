/**
 * 
 */
package cn.nju.game.ui.util;

/**
 * 召唤师模型转换工具工厂
 * @author frank
 *
 */
public class CommanderModelUtilFactory implements TableModelUtilFactory {

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.TableModelUtilFactory#createUtil()
	 */
	public ModelToTableModelUtil createUtil() {
		return new CommanderTableModelUtil(CommanderModelUtilFactory.class.getName());
	}

}
