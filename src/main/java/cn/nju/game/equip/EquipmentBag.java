package cn.nju.game.equip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 装备背包
 */
public class EquipmentBag implements Bag {

	private List<Equipment> equipments;
	
	protected class BagIterator implements ComponentIterator<Equipment> {

		private Iterator<Equipment> _it;
		
		public synchronized Equipment next() {
			return _it.next();
		}

		public boolean hasNext() {
			return _it.hasNext();
		}

		public BagIterator() {
			super();
			if (null == _it) {
				_it = equipments.iterator();
			}
		}
		
	}
	
    /**
     * Default constructor
     */
    public EquipmentBag() {
    	equipments = new ArrayList<Equipment>(CAPACITY);
    }

    /**
     * 产生背包迭代器
     * @return 背包迭代器，用于访问背包中每个项
     */
    public ComponentIterator<Equipment> iterator() {
    	return new BagIterator();
    }

}