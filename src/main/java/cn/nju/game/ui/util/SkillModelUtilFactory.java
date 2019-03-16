/**
 * 
 */
package cn.nju.game.ui.util;

/**
 * 技能模型转换工具工厂
 * @author frank
 *
 */
public class SkillModelUtilFactory implements TableModelUtilFactory {

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.TableModelUtilFactory#createUtil()
	 */
	public ModelToTableModelUtil createUtil() {
		return new SkillTableModelUtil(SkillModelUtilFactory.class.getName());
	}

}
