package com.things;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Indexes;
import io.micronaut.context.annotation.Context;
import jakarta.annotation.PostConstruct;

@Context
public class ThingRepositoryIndexCreator  {

    MongoClient mongoClient;

    ThingRepositoryIndexCreator(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PostConstruct
    public void init() {
        var collection = mongoClient.getDatabase("thingdb").getCollection("thing");

        collection.createIndex(Indexes.ascending("name"));
    }

}
