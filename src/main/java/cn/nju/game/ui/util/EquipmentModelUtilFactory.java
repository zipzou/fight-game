/**
 * 
 */
package cn.nju.game.ui.util;

/**
 * 装备模型转换工具工厂
 * @author frank
 *
 */
public class EquipmentModelUtilFactory implements TableModelUtilFactory {

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.TableModelUtilFactory#createUtil(java.lang.String)
	 */
	public ModelToTableModelUtil createUtil() {
		return new EquipmentTableModelUtil(EquipmentModelUtilFactory.class.getName());
	}

}
