/**
 * 
 */
package cn.nju.game.ui.util;

import java.io.InputStream;

import cn.nju.game.model.vo.CommanderBasicVO;

/**
 * 召唤师到模型的转换
 * @author frank
 *
 */
public class CommanderTableModelUtil extends ModelToTableModelUtil {

	public CommanderTableModelUtil(String forModel) {
		super(forModel);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getAttr2HeaderFile()
	 */
	@Override
	protected InputStream getAttr2HeaderFile() {
		return CommanderBasicVO.class.getResourceAsStream("commander-header.json");
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.ui.util.ModelToTableModelUtil#getHeader2IndexFile()
	 */
	@Override
	protected InputStream getHeader2IndexFile() {
		return CommanderBasicVO.class.getResourceAsStream("commander-index.json");
	}

}
