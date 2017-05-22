package com.example.viewings;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by trainer2 on 5/19/17.
 */
@Getter@Setter
@Entity(name="viewings")
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="show_id")
    private Long showId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="episode_id")
    private Long episodeId;

    private int timecode;

    @Column(name="updated_at")
    private Date updatedAt;
}
