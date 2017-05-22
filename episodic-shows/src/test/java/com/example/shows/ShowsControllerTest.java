package com.example.shows;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer2 on 5/18/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class ShowsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ShowRepository showRepository;



    @Test
    @Transactional
    @Rollback
    public void postCreateShow() throws Exception {

        Long count = showRepository.count();

        HashMap<String, Object> payload= new HashMap<String, Object>(){
            {
                put("name", "xyzShow");

            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json= mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("xyzShow") ))
                .andExpect(jsonPath("$.id", notNullValue() ));

    }


    @Test
    @Transactional
    @Rollback
    public void getShows() throws Exception {

        Show show = new Show();
        show.setName("xyzShow");

        showRepository.save(show);

        this.mvc.perform(get("/shows"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",equalTo("xyzShow") ))
                .andExpect(jsonPath("$[0].id", notNullValue() ));

    }







}
