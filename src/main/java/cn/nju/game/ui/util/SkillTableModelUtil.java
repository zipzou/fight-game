/**
 * 
 */
package cn.nju.game.ui.util;

import java.io.InputStream;

import cn.nju.game.model.vo.SkillVO;

/**
 * 技能对象到数据表模型的转换
 * @author frank
 *
 */
public class SkillTableModelUtil extends ModelToTableModelUtil {

	public SkillTableModelUtil(String forModel) {
		super(forModel);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getAttr2HeaderFile()
	 */
	@Override
	protected InputStream getAttr2HeaderFile() {
		return SkillVO.class.getResourceAsStream("skill-header.json");
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getHeader2IndexFile()
	 */
	@Override
	protected InputStream getHeader2IndexFile() {
		return SkillVO.class.getResourceAsStream("skill-index.json");
	}

}
