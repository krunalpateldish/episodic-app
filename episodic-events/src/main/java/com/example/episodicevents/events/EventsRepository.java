package com.example.episodicevents.events;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by trainer2 on 5/22/17.
 */
public interface EventsRepository extends MongoRepository<Events, String> {

}