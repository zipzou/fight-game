/**
 * 
 */
package cn.nju.game.conf;

import java.io.IOException;

/**
 * 配置文件接口
 * @author frank
 *
 */
public interface Configuration {

	/**
	 * 读取制定键的配置信息
	 * @param key 配置键
	 * @return 配置信息键对应的值，包括各类Java类型
	 */
	public Object read(String key);
	
	/**
	 * 存储制定键及键值信息
	 * @param key 配置键
	 * @param value 配置键对应的值信息
	 * @throws IOException 配置文件输出路径不可访问或文件不存或文件被占用无法写入
	 */
	public void save(String key, Object value) throws IOException;
}
