/**
 * 
 */
package cn.nju.game.serialize.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.util.ObjectiveSerializeUtil;
import cn.nju.game.weapon.DamageComputable;
import cn.nju.game.weapon.LeadingWeaponPart;
import cn.nju.game.weapon.TailWeaponPart;
import cn.nju.game.weapon.Weapon;

/**
 * @author frank
 *
 */
public class SerializeTest {

	DamageComputable weapon;
	@Before
	public void init() {
		weapon = new Weapon();
		weapon = new LeadingWeaponPart(weapon);
		weapon = new TailWeaponPart(weapon);
	}
	
	@Test
	public void testSerializeWeapon() throws IOException {
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString();
		ObjectiveSerializeUtil.serialize(folder, "test", weapon);
	}
	
	@Test
	public void testReadWeapon() throws ClassNotFoundException, IOException {
		String folder = GameConfiguration.sharedConfiguration().read(GameConfiguration.WEAPON_FILE).toString();
		DamageComputable weapon = ObjectiveSerializeUtil.unserialize(folder, "test");
		assertTrue(weapon.computeDamage() == this.weapon.computeDamage());
		System.out.println(weapon.computeDamage());
	}
	
}
