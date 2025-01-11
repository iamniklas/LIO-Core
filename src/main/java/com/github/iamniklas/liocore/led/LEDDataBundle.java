package com.github.iamniklas.liocore.led;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.interpolation.InterpolationType;
import com.github.iamniklas.liocore.musicsync.AnimationData;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LEDDataBundle {
    //used for monocolor, glitter, lighttoggle, lighttogglecineadjusted,
    public ColorRGB color;

    //used for multicolor
    public ArrayList<ColorRGB> colors;

    //used for javascript
    public String data;

    //used for lightning
    public Float attenuation;

    //used for lighttoggle, lighttogglecineadjusted
    public InterpolationType interpolation;

    //used for rainbow
    public Direction direction;
    public Float repetitions;

    //used for rainbow, rainbowmono, glitter
    public Float speed;

    //used for lightning, lighttoggle, lighttogglecineadjusted
    public Integer duration;

    //used in postupdate method
    public Integer modulo;
    public Boolean moduloInvert;


    public transient LEDStripManager ledStrip;
    public transient ProcedureCalls procedureCalls;

}
