/**
 * 
 */
package cn.nju.game.ui.util;

/**
 * 表格模型工具工厂
 * @author frank
 *
 */
public interface TableModelUtilFactory {

	/**
	 * 创建模型到表模型的转换工具
	 * @param factory 创建该工具的工厂
	 * @return 模型转换工具
	 */
	public ModelToTableModelUtil createUtil();
	
}
