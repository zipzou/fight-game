/**
 * 
 */
package cn.nju.game.role;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * 在线用户文件监听器
 * @author frank
 *
 */
public class OnlineFileListenerAdaptor extends FileAlterationListenerAdaptor {

	/* (non-Javadoc)
	 * @see org.apache.commons.io.monitor.FileAlterationListenerAdaptor#onStart(org.apache.commons.io.monitor.FileAlterationObserver)
	 */
	@Override
	public void onStart(FileAlterationObserver observer) {
		super.onStart(observer);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.io.monitor.FileAlterationListenerAdaptor#onFileChange(java.io.File)
	 */
	@Override
	public void onFileChange(File file) {
		super.onFileChange(file);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.io.monitor.FileAlterationListenerAdaptor#onStop(org.apache.commons.io.monitor.FileAlterationObserver)
	 */
	@Override
	public void onStop(FileAlterationObserver observer) {
		super.onStop(observer);
	}

}
