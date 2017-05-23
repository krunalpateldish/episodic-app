package com.example.episodicevents.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer2 on 5/22/17.
 */

@RestController
public class EventsController {

    @Autowired
    private final EventsRepository eventsRepository;

    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }


    @PostMapping("/")
    public Object createProduct(@RequestBody Events event) {
        this.eventsRepository.save(event);
        return event;
    }

    @GetMapping("/recent")
    public List<Events> getEvents() {
        return eventsRepository.findAll();
    }
}
