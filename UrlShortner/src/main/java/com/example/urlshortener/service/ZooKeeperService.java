package com.example.urlshortener.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZooKeeperService {
    @Autowired
    private CuratorFramework curatorFramework;

    public String generateUniqueId() throws Exception {
        return curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/");
    }
}
