package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer2 on 5/22/17.
 */

@JsonTypeName("fastForward")
@Getter
@Setter
@NoArgsConstructor
public class FastForwardEvent extends Events {

    private Data data;

//    public FastForwardEvent(String id, Long userId, Long showId, Long episodeId, Date createdAt, DataX data) {
//        super(id,userId, showId, episodeId, createdAt);
//        this.data=data;
//    }


    @Getter
    @Setter
    private static class Data{
        int startOffset;
        int endOffset;
        float speed;
    }

}