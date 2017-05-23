package com.example.viewings;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by trainer2 on 5/19/17.
 */
@Getter@Setter
@Entity
@Table(name="viewings")
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;

    @Column(name="show_id")
     Long showId;

    @Column(name="user_id")
     Long userId;

    @Column(name="episode_id")
     Long episodeId;

    private int timecode;

    @Column(name="updated_at")
     Date updatedAt;
}

