package com.github.iamniklas.liocore.led;

public class Area {
    int startIndex;
    int endIndex;

    public Area(int _start, int _end) {
        startIndex = _start;
        endIndex = _end;
    }

    public Area(Area _area) {
        startIndex = _area.startIndex;
        endIndex = _area.endIndex;
    }

    void redefineArea(int _start, int _end) {
        startIndex = _start;
        endIndex = _end;
    }
}
