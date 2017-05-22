package com.example.shows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer2 on 5/18/17.
 */
@RestController
@RequestMapping("/shows")
public class EpisodeController {

    @Autowired
    private final ShowRepository showRepository;

    @Autowired
    private final EpisodeRepository episodeRepository;


    public EpisodeController(ShowRepository showRepository, EpisodeRepository episodeRepository) {
        this.showRepository = showRepository;
        this.episodeRepository = episodeRepository;
    }

    @PostMapping("/{id}/episodes")
    public Episode createEpisode(@PathVariable Long id, @RequestBody Episode episode){

        episode.setShowId(id);
        return this.episodeRepository.save(episode);
    }

    @GetMapping("/{id}/episodes")
    public Iterable<Episode> allEpisodes() {

        return this.episodeRepository.findAll();
    }

}
