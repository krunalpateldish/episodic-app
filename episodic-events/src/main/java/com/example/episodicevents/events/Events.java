package com.example.episodicevents.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by trainer2 on 5/22/17.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible=true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayEvent.class, name = "play"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "pause"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "fastForward"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "rewind"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "progress"),
        @JsonSubTypes.Type(value = PlayEvent.class, name = "scrub")
        })
public class Events {

    @Id
    private String id;

    private Long userId;
    private Long showId;
    private Long episodeId;
    private Date createdAt;

}
