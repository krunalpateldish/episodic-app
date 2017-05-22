package com.example.episode;

import com.example.shows.Episode;
import com.example.shows.EpisodeRepository;
import com.example.shows.Show;
import com.example.shows.ShowRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer2 on 5/18/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class EpisodeControllerTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    ShowRepository showRepository;

    @Test
    @Transactional
    @Rollback
    public void postCreateEpisode() throws Exception {

        Long count = episodeRepository.count();

        HashMap<String, Object> payload= new HashMap<String, Object>(){
            {
                put("showId", "1");
                put("seasonNumber", "3");
                put("episodeNumber", "4");

            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json= mapper.writeValueAsString(payload);

        Show show = new Show();
        show.setName("xyzShow");

        Show show1 = showRepository.save(show);
        Long id = show1.getId();

        MockHttpServletRequestBuilder request = post("/shows/{id}/episodes", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.name",equalTo("xyzShow") ))
                .andExpect(jsonPath("$.id", notNullValue() ));

    }


    @Test
    @Transactional
    @Rollback
    public void getEpisode() throws Exception {

        Episode episode = new Episode();
        //episode.setShow_id(Long.valueOf(1));
        episode.setEpisodeNumber(3);
        episode.setSeasonNumber(2);

        episodeRepository.save(episode);

        Show show = new Show();
        show.setName("xyzShow");

        showRepository.save(show);

        this.mvc.perform(get("/shows/1/episodes"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name",equalTo("xyzShow") ))
                .andExpect(jsonPath("$[0].id", notNullValue() ));

    }





}
