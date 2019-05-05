package com.peng.curator;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatcher implements Watcher {

	public void process(WatchedEvent event) {
		System.out.println("触发watcher，节点路径为：" + event.getPath());
	}

}
