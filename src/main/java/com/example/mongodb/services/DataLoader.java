package com.example.mongodb.services;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
@Service
public class DataLoader implements CommandLineRunner {

    private final MongoManager mongoManager;

    @Override
    public void run(String... args) throws Exception {
        MongoCollection<Document> companies =
                mongoManager.createCollectionIfNotExists("companies");

        // db.companies.find()

        // $gt
//        new Document("$in", List.of("", ""));
        // db.compaies.find({ name: {"$in": ["avi", "aroma" ]} })
        // lt lower th
        // gt greater th
        // lte
        // gte

        Function<Document, Document> findOneFromCompanies = mongoManager.findOneFrom("companies");


        System.out.println(findOneFromCompanies.apply(new Document("", "")));

//        companies.find(new Document("name", new Document("$lt", "b")))
//                .projection(Projections.include("name", "street"))
//                .projection(Projections.exclude("_id"))
//                .iterator()
//                .forEachRemaining(System.out::println);


    }
}
