package com.base;

import org.bson.Document;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DatabaseClass {

    String DataBaseURL ="mongodb://development:bRbhKCWgaVrhkgKnkvqC@172.31.41.143:27017/?replicaSet=rs0";
    String Database="db_iksana_dev";
    String TBL_ONE_TIME_PASSWORD="tbl_one_time_password";




    @Test
    public void dbConnection(){

    MongoClient mongoClient = MongoClients.create(DataBaseURL);
		MongoDatabase db=mongoClient.getDatabase(Database);
		MongoCollection<Document> collection=db.getCollection(TBL_ONE_TIME_PASSWORD);
		FindIterable<Document> results=collection.find(new Document("phoneNo","6888877880"));

		for(Document document: results) {
			String output1=document.getString("otp");
            System.out.println(output1);
		}
    }
        
}
