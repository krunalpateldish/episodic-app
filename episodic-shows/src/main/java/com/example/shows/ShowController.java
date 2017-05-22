package com.example.shows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer2 on 5/18/17.
 */

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private final ShowRepository showRepository;


    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }



    @PostMapping
    public Show postShow(@RequestBody Show show){

        return this.showRepository.save(show);
    }

    @GetMapping
    public Iterable<Show> allShow() {

        return this.showRepository.findAll();
    }


}
