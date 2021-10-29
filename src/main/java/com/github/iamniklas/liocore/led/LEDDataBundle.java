package com.github.iamniklas.liocore.led;

import com.github.iamniklas.liocore.interpolation.InterpolationType;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;
import com.github.iamniklas.liocore.procedures.models.Direction;

public class LEDDataBundle {
    public ColorRGB colorPrimary;
    public ColorRGB colorSecondary;
    public ColorRGB colorTertiary;
    public String data;
    public float value1;
    public float value2;
    public float value3;
    public int modulo;
    public InterpolationType interpolation;
    public Direction direction;
    public int bpm;
    public float repetitions;
    public float speed;
    public int duration;
    public boolean pulsating;
    public String path;

    //subBundle
    public boolean isSubProcedure;
    public boolean indeterminate;
    public int puModulo;
    public boolean puModuloInvert;
    public ILedStripManager ledStrip;
    public ProcedureCalls procedureCalls;
}
