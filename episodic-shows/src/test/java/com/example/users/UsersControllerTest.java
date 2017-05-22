package com.example.users;

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
 * Created by trainer2 on 5/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class UsersControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;



//    @After
//    public void removeUsers() {
//        userRepository.deleteAll();
//    }


    @Test
    @Transactional
    @Rollback
    public void postCreateUser() throws Exception {

        Long count = userRepository.count();

        HashMap<String, Object> payload= new HashMap<String, Object>(){
            {
                put("email", "joe@example.com");

            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json= mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",equalTo("joe@example.com") ))
                .andExpect(jsonPath("$.id", notNullValue() ));

    }


    @Test
    @Transactional
    @Rollback
    public void getUser() throws Exception {

        User user = new User();

        user.setEmail("kp@example.com");


        userRepository.save(user);

        this.mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email",equalTo("kp@example.com") ))
                .andExpect(jsonPath("$[0].id", notNullValue() ));

    }

}
