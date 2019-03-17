/**
 * 
 */
package cn.nju.game.skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;

/**
 * 用于在界面上删除一个控件
 * @author frank
 *
 */
public class DeleteEquipItemListener implements ActionListener {

	private JComponent parent; // 父容器
	
	private int componentIndex; // 子组件所在索引
	
	private int dataIndex; // 数据对象索引
	
	private List<String> data; // 数据对象
	
	private Runnable callback; // 删除后的回调
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		parent.remove(componentIndex);
		data.remove(dataIndex);
		if (null != callback) {
			callback.run();
		}
	}


	/**
	 * @return the parent
	 */
	public JComponent getParent() {
		return parent;
	}


	/**
	 * @param parent the parent to set
	 */
	public void setParent(JComponent parent) {
		this.parent = parent;
	}


	/**
	 * @return the componentIndex
	 */
	public int getComponentIndex() {
		return componentIndex;
	}


	/**
	 * @param componentIndex the componentIndex to set
	 */
	public void setComponentIndex(int componentIndex) {
		this.componentIndex = componentIndex;
	}


	/**
	 * @return the dataIndex
	 */
	public int getDataIndex() {
		return dataIndex;
	}


	/**
	 * @param dataIndex the dataIndex to set
	 */
	public void setDataIndex(int dataIndex) {
		this.dataIndex = dataIndex;
	}


	/**
	 * @return the callback
	 */
	public Runnable getCallback() {
		return callback;
	}


	/**
	 * @param callback the callback to set
	 */
	public void setCallback(Runnable callback) {
		this.callback = callback;
	}


	/**
	 * @return the data
	 */
	public List<String> getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(List<String> data) {
		this.data = data;
	}
}
