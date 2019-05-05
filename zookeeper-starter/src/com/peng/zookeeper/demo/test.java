package com.peng.zookeeper.demo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class test implements Watcher {

	private ZooKeeper zookeeper = null;
	
	public static final String zkServerPath = "47.93.8.161:2184";
	public static final Integer timeout = 5000;
	
	public test() {}
	
	public test(String connectString) {
		try {
			zookeeper = new ZooKeeper(connectString, timeout, new test());
		} catch (IOException e) {
			e.printStackTrace();
			if (zookeeper != null) {
				try {
					zookeeper.close();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void createZKNode(String path, byte[] data, List<ACL> acls) {
		
		String result = "";
		try {
			result = zookeeper.create(path, data, acls, CreateMode.PERSISTENT);
			System.out.println("创建节点：\t" + result + "\t成功...");
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) throws Exception {
	
		test zkServer = new test(zkServerPath);
		
		/**
		 * ======================  创建node start  ======================  
		 */
		// acl 任何人都可以访问
//		zkServer.createZKNode("/aclimooc", "test".getBytes(), Ids.OPEN_ACL_UNSAFE);
		
		// ip方式的acl
//		List<ACL> aclsIP = new ArrayList<ACL>();
//		Id ipId1 = new Id("ip", "223.64.219.144");
//		aclsIP.add(new ACL(Perms.ALL, ipId1));
//		zkServer.createZKNode("/node-new-abc", "aabbcc".getBytes(), aclsIP);

		// 验证ip是否有权限
		zkServer.getZookeeper().setData("/node-new-abc", "abcd".getBytes(), 1);
		Stat stat = new Stat();
		byte[] data = zkServer.getZookeeper().getData("/node-new-abc", false, stat);
		System.out.println(new String(data));
		System.out.println(stat.getVersion());
	}

	public ZooKeeper getZookeeper() {
		return zookeeper;
	}
	public void setZookeeper(ZooKeeper zookeeper) {
		this.zookeeper = zookeeper;
	}

	@Override
	public void process(WatchedEvent event) {
		
	}
}

