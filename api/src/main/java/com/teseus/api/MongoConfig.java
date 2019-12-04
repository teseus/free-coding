package com.teseus.api;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${port}")
    private int port;

    @Value("${dbName}")
    private String dbName;

    @Override
    public MongoClient reactiveMongoClient() {
        return null;
    }

    @Override
    protected String getDatabaseName() {
        return null;
    }
}
