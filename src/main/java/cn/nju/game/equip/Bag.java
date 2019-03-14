package cn.nju.game.equip;
/**
 * 背包
 */
public interface Bag {

	static final int CAPACITY = 9;

	/**
	 * 产生背包迭代器
	 * 
	 * @return 背包迭代器，用于访问背包中每个项
	 */
    public ComponentIterator<Equipment> iterator();
    
}