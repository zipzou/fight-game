package cn.nju.game.role.factory;

import cn.nju.game.role.Commander;
import cn.nju.game.role.WarriorCommander;

/**
 * 
 */
public class WarriorFactory implements CommanderFactory {

    /**
     * Default constructor
     */
    public WarriorFactory() {
    }

	public Commander produceCommander(String name) {
		return WarriorCommander.builder()
				.health(300)
				.magicalDamage(0)
				.armor(5)
				.physicalDamage(10)
				.name(name)
				.magicalResistance(0)
				.expirence(0)
				.energy(200)
				.build();
	}

}