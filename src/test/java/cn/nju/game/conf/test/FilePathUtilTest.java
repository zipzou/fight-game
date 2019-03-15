package cn.nju.game.conf.test;

import org.junit.Test;

import cn.nju.game.conf.util.FilePathUtil;

public class FilePathUtilTest {

	@Test
	public void testMkdir() {
		FilePathUtil.mkdir("game/roles/roles.json");
	}
	
}
