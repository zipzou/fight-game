package cn.nju.game.equip;
/**
 * 组件迭代器
 */
public interface ComponentIterator<T> {

    /**
     * 
     */
    public T next();

    /**
     * 
     */
    public boolean hasNext();

}