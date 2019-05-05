package com.peng.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

public class MyCuratorWatcher implements CuratorWatcher {

	public void process(WatchedEvent event) throws Exception {
		System.out.println("触发watcher，节点路径为：" + event.getPath());
	}

}
