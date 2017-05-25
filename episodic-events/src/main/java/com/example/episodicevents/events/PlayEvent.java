package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer2 on 5/22/17.
 */

@JsonTypeName("play")
@Getter
@Setter
@NoArgsConstructor
public class PlayEvent extends Events {

    private Data data;

//    public PlayEvent(String id, Long userId, Long showId, Long episodeId, Date createdAt, DataX data) {
//        super(id,userId, showId, episodeId, createdAt);
//        this.data=data;
//    }

    @Setter
    @Getter
    private static class Data{
        private int offset;
    }


}