package cn.nju.game.equip;

import java.io.Serializable;

/**
 * 背包
 */
public interface Bag extends Serializable {

	static final int CAPACITY = 9;

	/**
	 * 产生背包迭代器
	 * 
	 * @return 背包迭代器，用于访问背包中每个项
	 */
    public ComponentIterator<Equipment> iterator();
    
    /**
     * 添加装备
     * @param equipment 装备
     */
    public void add(Equipment equipment);
    
    /**
     * 移除装备
     * @param equipment 装备
     */
    public void remove(Equipment equipment);
    
    /**
     * 移除装备
     * @param name 装备名
     */
    public void remove(String name);
    
}