package com.example;

import com.example.shows.Episode;
import com.example.shows.EpisodeRepository;
import com.example.viewings.Progress;
import com.example.viewings.Viewing;
import com.example.viewings.ViewingRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

//import java.util.Map;


/**
 * Created by trainer2 on 5/23/17.
 */
@Configuration
public class AmqpListener implements RabbitListenerConfigurer {

    private final EpisodeRepository episodeRepository;
    private final ViewingRepository viewingRepository;

    public AmqpListener(EpisodeRepository episodeRepository, ViewingRepository viewingRepository) {
        this.episodeRepository = episodeRepository;
        this.viewingRepository = viewingRepository;
    }


    @RabbitListener(queues = "episodic-progress")
    public void receiveMessage(Progress progress) {

        Viewing viewingReturned = viewingRepository.findViewingByEpisodeId(progress.getEpisodeId());

        if (null != viewingReturned) {
            viewingRepository.updateViewingEpisodeTimeCodebyUserId(progress.getEpisodeId(), progress.getCreatedAt(),
                    progress.getOffset(), progress.getUserId());
        }

        else {
            Episode returnedEpisode =  episodeRepository.findByEpisodeNumber(Math.toIntExact(progress.getEpisodeId()));
            Viewing viewing = new Viewing();
            viewing.setUpdatedAt(progress.getCreatedAt());
            viewing.setTimecode(progress.getOffset());
            viewing.setEpisodeId(progress.getEpisodeId());
            viewing.setShowId(returnedEpisode.getShowId());
            viewing.setUserId(progress.getUserId());
//            System.out.println("************************************************");
//            System.out.println(progress.toString());
//            System.out.println("************************************************");
            viewingRepository.save(viewing);
        }


    }








    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() { // <-- 2
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {  // <-- 3
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }


}