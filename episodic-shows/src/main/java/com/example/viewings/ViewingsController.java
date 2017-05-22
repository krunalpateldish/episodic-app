package com.example.viewings;

import com.example.shows.Episode;
import com.example.shows.EpisodeRepository;
import com.example.shows.Show;
import com.example.shows.ShowRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trainer2 on 5/19/17.
 */

@RestController
@RequestMapping("/users")
public class ViewingsController {



    private final ViewingRepository viewingRepository;


    private final ShowRepository showRepository;


    private final EpisodeRepository episodeRepository;

    public ViewingsController(ViewingRepository viewingRepository, ShowRepository showRepository, EpisodeRepository episodeRepository) {

        Assert.notNull(viewingRepository, "ViewingRepo cannot be null");

        Assert.notNull(showRepository, "ShowRepository cannot be null");
        Assert.notNull(episodeRepository, "EpisodeRepository cannot be null");

        this.viewingRepository = viewingRepository;
        this.showRepository = showRepository;
        this.episodeRepository = episodeRepository;

    }


    @PatchMapping("/{id}/viewings")
    public void patchViewing(@RequestBody Viewing viewing, @PathVariable("id") String userId) {

        viewing.setUserId(Long.parseLong(userId));

        //Take userId and get show from show repository.
        this.viewingRepository.updateViewingEpisodeTimeCodebyUserId(viewing.getEpisodeId(),
                viewing.getUpdatedAt(),viewing.getTimecode(),Long.parseLong(userId));

    }


    @PostMapping("/{id}/viewings")
    public void createViewing(@RequestBody Viewing viewing, @PathVariable("id") String userId) {

        viewing.setUserId(Long.parseLong(userId));

        //Take userId and get show from show repository.
        this.viewingRepository.save(viewing);

    }

    @GetMapping("{id}/recently-watched")
    public List<Show_Episode_By_User> getRecentlyWatchedShow_EpisodeByUser(@PathVariable("id") String userId) {

        List<Show_Episode_By_User> allShowEpisodesByUser = new ArrayList<>();

        //Now build the ShowEpisodeByUser object

        List<Viewing> returnedViewingList = this.viewingRepository.findAllByUserIdOrderByUpdatedAt(Long.parseLong(userId));

        Show_Episode_By_User show_episode_by_user = new Show_Episode_By_User();



        Show returnedShow = showRepository.findById(returnedViewingList.get(0).getShowId());

        show_episode_by_user.setShow(returnedShow);

        Episode returnedEpisode = episodeRepository.findByShowId(returnedViewingList.get(0).getShowId());

        returnedEpisode.setTitle("S"+returnedEpisode.getShowId() + " E"+returnedEpisode.getSeasonNumber());

        show_episode_by_user.setShow(returnedShow);
        show_episode_by_user.setEpisode(returnedEpisode);

        show_episode_by_user.setUpdatedAt(returnedViewingList.get(0).getUpdatedAt());
        show_episode_by_user.setTimecode(returnedViewingList.get(0).getTimecode());

        allShowEpisodesByUser.add(show_episode_by_user);

        return allShowEpisodesByUser;
    }



}
