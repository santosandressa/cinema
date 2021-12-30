package com.cinema.tickets.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "cinema";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.cinema.tickets");
    }
}
