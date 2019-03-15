package cn.nju.game.equip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 装备背包
 */
public class EquipmentBag implements Bag {

	private static final long serialVersionUID = 2842680306983932250L;
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

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Bag#add(cn.nju.game.equip.Equipment)
	 */
	public void add(Equipment equipment) {
		if (CAPACITY <= equipments.size()) {
			return;
		}
		equipments.add(equipment);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Bag#remove(cn.nju.game.equip.Equipment)
	 */
	public void remove(Equipment equipment) {
		equipments.remove(equipment);
	}

	/* (non-Javadoc)
	 * @see cn.nju.game.equip.Bag#remove(java.lang.String)
	 */
	public void remove(String name) {
		ComponentIterator<Equipment> equipmentIterator = iterator();
		while (equipmentIterator.hasNext()) {
			Equipment curEquipment = equipmentIterator.next();
			if (name.equalsIgnoreCase(curEquipment.getName())) {
				remove(curEquipment);
				break;
			}
		}
	}

}