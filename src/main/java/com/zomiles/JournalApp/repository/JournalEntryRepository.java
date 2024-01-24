package com.zomiles.JournalApp.repository;


import com.zomiles.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    // Your repository methods go here
}
