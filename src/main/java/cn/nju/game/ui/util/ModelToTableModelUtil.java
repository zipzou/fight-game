/**
 * 
 */
package cn.nju.game.ui.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 将数据模型转换为表模型
 * @author frank
 *
 */
public abstract class ModelToTableModelUtil {
	
	private final String forModel;
	private JSONObject attr2Header = null;
	private JSONObject header2Index = null;
	
	public ModelToTableModelUtil(String forModel) {
		super();
		this.forModel = forModel;
	}

	/**
	 * 根据模型获得表头及表值模型
	 * @param model 模型数据
	 * @return 表模型，<i>两行</i>, <b>第一行</b>，表示表头所有的列，<b>第二行</b>，表示每行的全部列
	 * @throws IOException 当配置映射文件未在指定位置或配置映射文件无法被正确读取时抛出该异常，请确保文件放置并配置正确，并且设置为合法的<code>*.json</code>文件
	 */
	public final Object[][] transferToModel(Object model) throws IOException {
		Object[][] headerAndValue = new Object[2][];
		JSONObject modelJson = (JSONObject) JSON.toJSON(model); // 将对象转为JSON对象，具有map性质

		// 先获得实体属性名到表头名的映射
		attr2Header = getAttr2HeaderJson();
		
		// 再获取表头名到索引顺序的映射
		header2Index = getHeader2IndexJson();
		
		// 将值按照索引顺序进行构造
		Set<String> attrs = attr2Header.keySet(); // 获取所有属性
		// 将所有属性及值映射
		Map<String, Object> keyValueMap = new HashMap<String, Object>();
		for (String attr : attrs) { // 遍历所有属性
			StringTokenizer attrSplitTokenizer = new StringTokenizer(attr, ".");
			Object attrValue = modelJson.get(attrSplitTokenizer.nextToken());
			while(attrSplitTokenizer.hasMoreTokens()) {
				String key = attrSplitTokenizer.nextToken(); // 分层获取key
				if (null != attrValue && attrValue instanceof JSONObject)
					attrValue = (JSONObject) modelJson.get(key);
			}
			// 获取最终对象值
			keyValueMap.put(attr, attrValue);
		}
		
		// 构建属性值数组
		Object[] values = new Object[attr2Header.size()];
		Object[] headers = new Object[attr2Header.size()];
		Iterator<String> attrIterator = attrs.iterator();
		while (attrIterator.hasNext()) {
			String attr = attrIterator.next();
			if (header2Index.containsKey(attr)) {
				values[header2Index.getIntValue(attr)] = keyValueMap.get(attr);
				headers[header2Index.getIntValue(attr)] = attr2Header.get(attr);
				attrIterator.remove();
			}
		}
		for (String attr : attrs) { // 遍历所有属性，以添加值
			// 找寻空位插入
			for (int i = 0; i < values.length; i++) {
				if (null == values[i]) {
					// 插入
					values[i] = keyValueMap.get(attr);
					headers[i] = attr2Header.get(attr);
					break;
				}
			}
		}
		headerAndValue[0] = headers;
		headerAndValue[1] = values;
		return headerAndValue;
	}
	
	/**
	 * 根据模型获得表头模型
	 * @param model 模型对象
	 * @return 表模型，表示表头所有的列
	 * @throws FileNotFoundException 当配置映射文件未在指定位置时，抛出该异常，请确保文件放置并配置正确
	 * @throws IOException 当配置映射文件无法被正确读取时产生该异常，请确定配置映射文件格式正确，为合法的<code>*.json</code>文件
	 */
	public final Object[] transferModelToHeader(Object model) throws FileNotFoundException, IOException {
		JSONObject modelJson = (JSONObject) JSON.toJSON(model); // 将对象转为JSON对象，具有map性质

		// 先获得实体属性名到表头名的映射
		attr2Header = getAttr2HeaderJson();
		
		// 再获取表头名到索引顺序的映射
		header2Index = getHeader2IndexJson();
		
		// 将值按照索引顺序进行构造
		Set<String> attrs = attr2Header.keySet(); // 获取所有属性
		// 将所有属性及值映射
		Map<String, Object> keyValueMap = new HashMap<String, Object>();
		for (String attr : attrs) { // 遍历所有属性
			StringTokenizer attrSplitTokenizer = new StringTokenizer(attr, ".");
			Object attrValue = modelJson.get(attrSplitTokenizer.nextToken());
			while(attrSplitTokenizer.hasMoreTokens()) {
				String key = attrSplitTokenizer.nextToken(); // 分层获取key
				if (null != attrValue && attrValue instanceof JSONObject)
					attrValue = ((JSONObject) attrValue).get(key);
			}
			// 获取最终对象值
			keyValueMap.put(attr, attrValue);
		}
		
		// 构建属性值数组
		Object[] headers = new Object[attr2Header.size()];
		Iterator<String> attrIterator = attrs.iterator();
		while (attrIterator.hasNext()) {
			String attr = attrIterator.next();
			if (header2Index.containsKey(attr)) {
				headers[header2Index.getIntValue(attr)] = attr2Header.get(attr);
				attrIterator.remove();
			}
		}
		for (String attr : attrs) { // 遍历所有属性，以添加值
			// 找寻空位插入
			for (int i = 0; i < headers.length; i++) {
				if (null == headers[i]) {
					// 插入
					headers[i] = attr2Header.get(attr);
					break;
				}
			}
		}
		return headers;
	}
	
	/**
	 * 根据模型获得表行模型
	 * @param model 模型对象
	 * @return 表模型，表示表行所有的列
	 * @throws FileNotFoundException 当配置映射文件未在指定位置时，抛出该异常，请确保文件放置并配置正确
	 * @throws IOException 当配置映射文件无法被正确读取时产生该异常，请确定配置映射文件格式正确，为合法的<code>*.json</code>文件
	 */
	public final Object[] transferModelToValues(Object model) throws FileNotFoundException, IOException {
		JSONObject modelJson = (JSONObject) JSON.toJSON(model); // 将对象转为JSON对象，具有map性质

		// 先获得实体属性名到表头名的映射
		attr2Header = getAttr2HeaderJson();
		
		// 再获取表头名到索引顺序的映射
		header2Index = getHeader2IndexJson();
		
		// 将值按照索引顺序进行构造
		Set<String> attrs = attr2Header.keySet(); // 获取所有属性
		// 将所有属性及值映射
		Map<String, Object> keyValueMap = new HashMap<String, Object>();
		for (String attr : attrs) { // 遍历所有属性
			StringTokenizer attrSplitTokenizer = new StringTokenizer(attr, ".");
			Object attrValue = modelJson.get(attrSplitTokenizer.nextToken());
			while(attrSplitTokenizer.hasMoreTokens()) {
				String key = attrSplitTokenizer.nextToken(); // 分层获取key
				if (null != attrValue && attrValue instanceof JSONObject)
					attrValue = ((JSONObject) attrValue).get(key);
			}
			// 获取最终对象值
			keyValueMap.put(attr, attrValue);
		}
		
		// 构建属性值数组
		Object[] values = new Object[attr2Header.size()];
		Iterator<String> attrIterator = attrs.iterator();
		while (attrIterator.hasNext()) {
			String attr = attrIterator.next();
			if (header2Index.containsKey(attr)) {
				values[header2Index.getIntValue(attr)] = keyValueMap.get(attr);
				attrIterator.remove();
			}
		}
		for (String attr : attrs) { // 遍历所有属性，以添加值
			// 找寻空位插入
			for (int i = 0; i < values.length; i++) {
				if (null == values[i]) {
					// 插入
					values[i] = keyValueMap.get(attr);
					break;
				}
			}
		}
		return values;
	}

	/**
	 * 获取配置文件中配置的表头及列索引顺序映射
	 * @return 映射配置json对象
	 * @throws FileNotFoundException 当配置映射文件未在指定位置时，抛出该异常，请确保文件放置并配置正确
	 * @throws IOException 当配置映射文件无法被正确读取时产生该异常，请确定配置映射文件格式正确，为合法的<code>*.json</code>文件
	 */
	private JSONObject getHeader2IndexJson() throws FileNotFoundException, IOException {
		InputStream header2IndexInput = getHeader2IndexFile();
		String header2IndexJsonStr = IOUtils.toString(header2IndexInput, "utf-8");
		header2IndexInput.close();
		return JSON.parseObject(header2IndexJsonStr);
	}

	/**
	 * 获取配置文件中配置的对象属性及表头值的映射，对象属性支持使用<code>a.b</code>形式进行索引
	 * @return 映射配置json对象
	 * @throws FileNotFoundException 当配置映射文件未在指定位置时，抛出该异常，请确保文件放置并配置正确
	 * @throws IOException 当配置映射文件无法被正确读取时产生该异常，请确定配置映射文件格式正确，为合法的<code>*.json</code>文件
	 */
	private JSONObject getAttr2HeaderJson() throws FileNotFoundException, IOException {
		InputStream attr2HeaderInput = getAttr2HeaderFile();
		String attr2HeaderJsonStr = IOUtils.toString(attr2HeaderInput, "utf-8");
		attr2HeaderInput.close();
		return JSON.parseObject(attr2HeaderJsonStr);
	}
	
	/**
	 * 获取实体属性名到表头名的映射文件路径
	 * @return 映射文件路径
	 */
	protected abstract InputStream getAttr2HeaderFile();
	
	/**
	 * 取表头名到索引顺序的映射文件路径
	 * @return 映射文件路径
	 */
	protected abstract InputStream getHeader2IndexFile();

	public String getForModel() {
		return forModel;
	}
}
