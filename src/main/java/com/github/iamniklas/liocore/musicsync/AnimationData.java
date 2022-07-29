package com.github.iamniklas.liocore.musicsync;

import com.github.iamniklas.liocore.network.LEDUpdateModel;

import java.util.ArrayList;

public class AnimationData {
    public Meta meta;
    public ArrayList<Action> actions;

    public class Meta {
        public String data;
        public long offset;
    }

    public class Action {
        public long timestamp;
        public LEDUpdateModel updateModel;
        public Recurring recurring;

        public class Recurring {
            public boolean recurring;
            public long count;
            public long offset;
        }
    }
}
