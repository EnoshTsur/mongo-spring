package com.example.mongodb.services;

import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class MongoManager {

    // collection -> table
    /*
        users:
        ObjectId (5adfhj2363242dfsdfs) > ObjectId(4dgsfvjksdfsdfgsdg)

            1 | { name: "enosh", age: 30 , car: "toyota" }
            2 | { name: "efrat", food: "apple" }
            3 | { name: "yoni", friends: [ "enosh, "efrat" ] }
     */

    // implement command line runner as a service
    // autowire your mongo manager
    // create collection which does not exists
    // look up for it in your cloud db

    private final MongoTemplate mongoTemplate;

    public boolean isCollectionExists(String name) {
        return mongoTemplate.collectionExists(name);
    }

    public MongoCollection<Document> getCollection(String name) {
        return mongoTemplate.getCollection(name);
    }

    public MongoCollection<Document> createCollectionIfNotExists(String name) {
        return isCollectionExists(name) ? getCollection(name) :
                mongoTemplate.createCollection(name);
    }

    public Function<Document, Document> findOneFrom(String collectionName) {
        return query -> Optional.of(
                getCollection(collectionName).find(query).iterator()
        )
                .filter(Iterator::hasNext)
                .map(Iterator::next)
                .orElse(null);
    }
}
