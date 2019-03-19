/**
 * 
 */
package cn.nju.game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import cn.nju.game.conf.util.FilePathUtil;

/**
 * 装备序列化
 * @author frank
 *
 */
public class ObjectiveSerializeUtil {

	private static final String SUBFIX = ".dt";

	/**
	 * 将目标序列化到文件
	 * @param folder 序列化文件目录
	 * @param name 保存的文件名
	 * @param t 目标对象
	 * @return 保存后的文件路径
	 * @throws IOException 如果文件不存在或无法写入，将会抛出IO异常
	 */
	public static <T> String serialize(String folder, String name, T t) throws IOException {
		String filePath = new File(new File(FilePathUtil.mkdir(folder)), name + SUBFIX).getAbsolutePath();
		FilePathUtil.mkdir(filePath);
		OutputStream equipmentOutput = new FileOutputStream(filePath);
		ObjectOutputStream equipmentObjectOutput = new ObjectOutputStream(equipmentOutput);
		equipmentObjectOutput.writeObject(t);
		equipmentObjectOutput.close();
		equipmentOutput.close();
		return filePath;
	}
	
//	public static String serialize(String name, Bag eqyipmentBag) throws IOException {
//		String equipmentFilePath = GameConfiguration.sharedConfiguration().read(GameConfiguration.EQUIPMENT_FILE).toString();
//		String filePath = new File(FilePathUtil.mkdir(equipmentFilePath), name + SUBFIX).getAbsolutePath();
//		OutputStream equipmentOutput = new FileOutputStream(filePath);
//		ObjectOutputStream equipmentObjectOutput = new ObjectOutputStream(equipmentOutput);
//		equipmentObjectOutput.writeObject(eqyipmentBag);
//		equipmentObjectOutput.close();
//		equipmentOutput.close();
//		return filePath;
//	}
	
	/**
	 * 将文件反序列化至对象
	 * @param folder 序列化文件目录
	 * @param name 文件名
	 * @throws IOException 如果文件不存在或无法写入，将会抛出IO异常
	 * @throws ClassNotFoundException 发生无法进行的类型转换时抛出，这通常是序列化对象类型与接收对象不属于同一个对象导致
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(String folder, String name) throws IOException, ClassNotFoundException {
		File targetFile = new File(folder, name + SUBFIX);
		if (!targetFile.exists()) {
			return null;
		}
		InputStream input = new FileInputStream(targetFile);
		ObjectInputStream objectInputStream = new ObjectInputStream(input);
		T target = ((T) objectInputStream.readObject());
		objectInputStream.close();
		input.close();
		return target;
	}
	
}
