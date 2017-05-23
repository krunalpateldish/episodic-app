package com.example.episodicevents;

import com.example.episodicevents.events.EventsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer2 on 5/22/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private EventsRepository eventsRepository;

    @Before
    public void setUp() {
        eventsRepository.deleteAll();
    }



    @Test
    public void postPlayEvents() throws Exception {

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "play");
                put("userId", 1);
                put("showId", 1);
                put("episodeId", 2);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 0);
                    }
                });
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void postPauseEvents() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "pause");
                put("userId", 52);
                put("showId", 78);
                put("episodeId", 34);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 0);
                    }
                });
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void postFasrForwardEvent() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "fastForward");
                put("userId", 52);
                put("showId", 78);
                put("episodeId", 34);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("startOffset", 4);
                        put("endOffset", 408);
                        put("speed", 2.5);
                    }
                });
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void postRewind() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "rewind");
                put("userId", 52);
                put("showId", 78);
                put("episodeId", 34);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("startOffset", 4);
                        put("endOffset", 408);
                        put("speed", 2.5);
                    }
                });
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void postProgressEvent() throws Exception {
        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "progress");
                put("userId", 52);
                put("showId", 78);
                put("episodeId", 34);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset", 4);
                    }
                });
            }
        };
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc.perform(request)
                .andExpect(status().isOk());
    }
    

}
