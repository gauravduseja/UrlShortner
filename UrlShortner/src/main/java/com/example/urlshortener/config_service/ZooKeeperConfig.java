package com.example.urlshortener.config_service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooKeeperConfig {
    @Value("${zookeeper.connectionString}")
    private String zookeeperConnectionString;

    private CuratorFramework curatorFramework;

    @PostConstruct
    public void init() {
        curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, new RetryForever(2));
        curatorFramework.start();
    }

    @PreDestroy
    public void close() {
        curatorFramework.close();
    }

    @Bean
    public CuratorFramework curatorFramework() {
        return curatorFramework;
    }
}