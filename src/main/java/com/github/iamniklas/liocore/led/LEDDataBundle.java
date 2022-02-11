package com.github.iamniklas.liocore.led;

import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.interpolation.InterpolationType;
import com.github.iamniklas.liocore.procedures.ProcedureCalls;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.google.gson.annotations.SerializedName;

public class LEDDataBundle {
    @SerializedName("color_primary")
    public ColorRGB colorPrimary;
    @SerializedName("color_secondary")
    public ColorRGB colorSecondary;
    @SerializedName("color_tertiary")
    public ColorRGB colorTertiary;
    public String data;
    @SerializedName("value_1")
    public Float value1;
    @SerializedName("value_2")
    public Float value2;
    @SerializedName("value_3")
    public Float value3;
    public Integer modulo;
    public InterpolationType interpolation;
    public Direction direction;
    public Integer bpm;
    public Float repetitions;
    public Float speed;
    public Integer duration;
    public Boolean pulsating;
    public String path;

    //subBundle
    @SerializedName("is_sub_procedure")
    public Boolean isSubProcedure;
    public Boolean indeterminate;
    @SerializedName("pu_modulo")
    public Integer puModulo;
    @SerializedName("pu_modulo_invert")
    public Boolean puModuloInvert;
    public LEDStripManager ledStrip;
    public ProcedureCalls procedureCalls;
}
