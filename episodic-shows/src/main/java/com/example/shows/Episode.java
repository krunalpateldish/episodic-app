package com.example.shows;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer2 on 5/18/17.
 */

@Getter
@Setter
@Entity(name="episodes")

public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;

    private int episodeNumber;

    private int seasonNumber;

//    @Persistent
//    public String title;

    @Transient
    @JsonProperty("title")
    String title;


//    @Transient

    private String getTitle(){
        return "S" + this.seasonNumber + " " + "E" + this.episodeNumber;
    }
}
