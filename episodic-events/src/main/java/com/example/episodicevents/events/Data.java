package com.example.episodicevents.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer2 on 5/22/17.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private int offset;
    private int startOffset;
    private int endOffset;
    private float speed;

    public Data(int startOffset, int endOffset, float speed) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.speed = speed;
    }

    public Data(int offset) {
        this.offset = offset;
    }

}