package com.layout.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String CONNECTION_URL = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "layoutDB";

    private static MongoClient mongoClient;

    // This method returns the database object
    public static MongoDatabase getDatabase() {

        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_URL);
        }

        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
