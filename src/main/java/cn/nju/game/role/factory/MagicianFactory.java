package cn.nju.game.role.factory;

import cn.nju.game.role.Commander;
import cn.nju.game.role.MagicianCommander;

/**
 * 
 */
public class MagicianFactory implements CommanderFactory {

    /**
     * Default constructor
     */
    public MagicianFactory() {
    }

	public Commander produceCommander(String name) {
		return MagicianCommander.builder()
				.armor(3)
				.energy(220)
				.expirence(0)
				.magicalDamage(5)
				.magicalResistance(0)
				.physicalDamage(3)
				.name(name)
				.build();
	}

}