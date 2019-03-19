package cn.nju.game.conf.test;

import java.io.IOException;

import org.junit.Test;

import cn.nju.game.conf.Configuration;
import cn.nju.game.conf.game.GameConfiguration;

public class GameConfigurationTest {

	@Test
	public void testWrite() {
		Configuration config = GameConfiguration.sharedConfiguration();
		try {
			config.save("test", "val");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(config.read("test"));
	}
	
	@Test
	public void testRead() {
		Configuration config = GameConfiguration.sharedConfiguration();
		System.out.println(config.read("test"));
	}
}
