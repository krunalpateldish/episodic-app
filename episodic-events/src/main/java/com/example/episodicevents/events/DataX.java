package com.example.episodicevents.events;

/**
 * Created by trainer2 on 5/22/17.
 */


public class DataX {
    private int offset;
    private int startOffset;
    private int endOffset;
    private float speed;

    public DataX(int startOffset, int endOffset, float speed) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.speed = speed;
    }

    public DataX(int offset) {
        this.offset = offset;
    }

}