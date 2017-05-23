package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by trainer2 on 5/22/17.
 */

@JsonTypeName("rewind")
@Getter
@Setter
@NoArgsConstructor
public class RewindEvent extends Events {

    private Data data;

    public RewindEvent(String id, Long userId, Long showId, Long episodeId, Date createdAt, Data data) {
        super(id,userId, showId, episodeId, createdAt);
        this.data=data;
    }



}