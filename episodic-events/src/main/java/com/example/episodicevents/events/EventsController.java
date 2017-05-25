package com.example.episodicevents.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trainer2 on 5/22/17.
 */

@RestController
public class EventsController {

    private final EventsRepository eventsRepository;
    private final RabbitTemplate rabbitTemplate;

    public EventsController(EventsRepository eventsRepository, RabbitTemplate rabbitTemplate) {
        this.eventsRepository = eventsRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping("/")
    public Object createProduct(@RequestBody Events event) {
        this.eventsRepository.save(event);
        if(event.getType().equals("progress")){
            EventMessage eventMessage = new EventMessage();
            eventMessage.setUserId(event.getUserId());
            eventMessage.setEpisodeId(event.getEpisodeId());
            eventMessage.setOffset(((ProgressEvent) event).getData().getOffset());
            eventMessage.setCreatedAt(event.getCreatedAt());
            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key",eventMessage);
        }
        return event;
    }

    @GetMapping("/recent")
    public List<Events> getEvents() {
        return eventsRepository.findAll();
    }
}
