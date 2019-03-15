/**
 * 
 */
package cn.nju.game.role.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.nju.game.conf.game.GameConfiguration;
import cn.nju.game.role.Commander;
import cn.nju.game.role.factory.MagicianFactory;
import cn.nju.game.role.factory.WarriorFactory;
import cn.nju.game.service.CommanderPool;
import cn.nju.game.util.ObjectiveSerializeUtil;

/**
 * @author frank
 *
 */
public class RoleTest {

	List<String> files = new ArrayList<String>();
	
	@Before
	public void writeCommanders() {
		String rolesPath = GameConfiguration.sharedConfiguration().read(GameConfiguration.ROLES_FILE).toString();
		Commander commander = new WarriorFactory().produceCommander("zzp");
		try {
			String filePath = ObjectiveSerializeUtil.serialize(rolesPath, commander.getName(), commander);
			files.add(filePath);
			commander = new WarriorFactory().produceCommander("测试");
			filePath = ObjectiveSerializeUtil.serialize(rolesPath, commander.getName(), commander);
			files.add(filePath);
			
			commander = new WarriorFactory().produceCommander("战士");
			filePath = ObjectiveSerializeUtil.serialize(rolesPath, commander.getName(), commander);
			files.add(filePath);
			
			commander = new MagicianFactory().produceCommander("法师");
			filePath = ObjectiveSerializeUtil.serialize(rolesPath, commander.getName(), commander);
			files.add(filePath);
			
			commander = new MagicianFactory().produceCommander("测试法师");
			filePath = ObjectiveSerializeUtil.serialize(rolesPath, commander.getName(), commander);
			files.add(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readCommanders() {
		CommanderPool pool = new CommanderPool();
		assertTrue(pool.get("zzp").getName().equalsIgnoreCase("zzp"));
	}
	
}
