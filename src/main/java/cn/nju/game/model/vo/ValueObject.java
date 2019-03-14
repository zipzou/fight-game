/**
 * 
 */
package cn.nju.game.model.vo;

import java.io.Serializable;

import org.dozer.DozerBeanMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.nju.game.role.Commander;

/**
 * @author frank
 *
 */
public abstract class ValueObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6316028195999500583L;

	/**
	 * 获取属性值
	 * @param attr 属性名
	 * @return 属性值
	 */
	public Object get(String attr) {
		JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
		return jsonObject.get(attr);
	}
	
	public static ValueObject fromCommander(Commander commander, Class<?> target) {
		return (ValueObject) new DozerBeanMapper().map(commander, target);
	}
}
