/**
 * 
 */
package cn.nju.game.conf.util;

import java.io.File;

/**
 * 文件路径工具
 * @author frank
 *
 */
public class FilePathUtil {

	/**
	 * 创建目录
	 * @param path
	 */
	public static String mkdir(String path) {
		String userDir = System.getProperty("user.dir");
		File userFolder = new File(userDir);
		File subFolder = new File(userFolder, path);
		
		if (subFolder.isFile()) {
			subFolder.getParentFile().mkdirs();
			return subFolder.getPath();
		} else {
			subFolder.mkdirs();
			return subFolder.getPath();
		}
	}
	
}
