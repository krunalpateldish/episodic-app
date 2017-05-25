package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer2 on 5/22/17.
 */

@JsonTypeName("progress")
@Getter
@Setter
@NoArgsConstructor
public class ProgressEvent extends Events {

    private Data data;

//    public ProgressEvent(String id, Long userId, Long showId, Long episodeId, Date createdAt, DataX data) {
//        super(id,userId, showId, episodeId, createdAt);
//        this.data=data;
//    }


    public static class Data{
        int offset;

        public int getOffset(){
            return offset;
        }

        public void setOffset(int offset){
            this.offset = offset;
        }
    }


}