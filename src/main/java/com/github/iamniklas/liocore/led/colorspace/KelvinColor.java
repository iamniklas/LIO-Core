package com.github.iamniklas.liocore.led.colorspace;

import com.github.iamniklas.colorspaces.ColorRGB;

import java.util.Map;
import java.util.TreeMap;

public class KelvinColor {
    private static final TreeMap<Integer, int[]> kelvinTable = new TreeMap<>();

    static {
        kelvinTable.put(1000, new int[]{255, 56, 0});
        kelvinTable.put(1100, new int[]{255, 71, 0});
        kelvinTable.put(1200, new int[]{255, 83, 0});
        kelvinTable.put(1300, new int[]{255, 93, 0});
        kelvinTable.put(1400, new int[]{255, 101, 0});
        kelvinTable.put(1500, new int[]{255, 109, 0});
        kelvinTable.put(1600, new int[]{255, 115, 0});
        kelvinTable.put(1700, new int[]{255, 121, 0});
        kelvinTable.put(1800, new int[]{255, 126, 0});
        kelvinTable.put(1900, new int[]{255, 131, 0});
        kelvinTable.put(2000, new int[]{255, 138, 18});
        kelvinTable.put(2100, new int[]{255, 142, 33});
        kelvinTable.put(2200, new int[]{255, 147, 44});
        kelvinTable.put(2300, new int[]{255, 152, 54});
        kelvinTable.put(2400, new int[]{255, 157, 63});
        kelvinTable.put(2500, new int[]{255, 161, 72});
        kelvinTable.put(2600, new int[]{255, 165, 79});
        kelvinTable.put(2700, new int[]{255, 169, 87});
        kelvinTable.put(2800, new int[]{255, 173, 94});
        kelvinTable.put(2900, new int[]{255, 177, 101});
        kelvinTable.put(3000, new int[]{255, 180, 107});
        kelvinTable.put(3100, new int[]{255, 184, 114});
        kelvinTable.put(3200, new int[]{255, 187, 120});
        kelvinTable.put(3300, new int[]{255, 190, 126});
        kelvinTable.put(3400, new int[]{255, 193, 132});
        kelvinTable.put(3500, new int[]{255, 196, 137});
        kelvinTable.put(3600, new int[]{255, 199, 143});
        kelvinTable.put(3700, new int[]{255, 201, 148});
        kelvinTable.put(3800, new int[]{255, 204, 153});
        kelvinTable.put(3900, new int[]{255, 206, 159});
        kelvinTable.put(4000, new int[]{255, 209, 163});
        kelvinTable.put(4100, new int[]{255, 211, 168});
        kelvinTable.put(4200, new int[]{255, 213, 173});
        kelvinTable.put(4300, new int[]{255, 215, 177});
        kelvinTable.put(4400, new int[]{255, 217, 182});
        kelvinTable.put(4500, new int[]{255, 219, 186});
        kelvinTable.put(4600, new int[]{255, 221, 190});
        kelvinTable.put(4700, new int[]{255, 223, 194});
        kelvinTable.put(4800, new int[]{255, 225, 198});
        kelvinTable.put(4900, new int[]{255, 227, 202});
        kelvinTable.put(5000, new int[]{255, 228, 206});
        kelvinTable.put(5100, new int[]{255, 230, 210});
        kelvinTable.put(5200, new int[]{255, 232, 213});
        kelvinTable.put(5300, new int[]{255, 233, 217});
        kelvinTable.put(5400, new int[]{255, 235, 220});
        kelvinTable.put(5500, new int[]{255, 236, 224});
        kelvinTable.put(5600, new int[]{255, 238, 227});
        kelvinTable.put(5700, new int[]{255, 239, 230});
        kelvinTable.put(5800, new int[]{255, 240, 233});
        kelvinTable.put(5900, new int[]{255, 242, 236});
        kelvinTable.put(6000, new int[]{255, 243, 239});
        kelvinTable.put(6100, new int[]{255, 244, 242});
        kelvinTable.put(6200, new int[]{255, 245, 245});
        kelvinTable.put(6300, new int[]{255, 246, 247});
        kelvinTable.put(6400, new int[]{255, 248, 251});
        kelvinTable.put(6500, new int[]{255, 249, 253});
        kelvinTable.put(6600, new int[]{254, 249, 255});
        kelvinTable.put(6700, new int[]{252, 247, 255});
        kelvinTable.put(6800, new int[]{249, 246, 255});
        kelvinTable.put(6900, new int[]{247, 245, 255});
        kelvinTable.put(7000, new int[]{245, 243, 255});
        kelvinTable.put(7100, new int[]{243, 242, 255});
        kelvinTable.put(7200, new int[]{240, 241, 255});
        kelvinTable.put(7300, new int[]{239, 240, 255});
        kelvinTable.put(7400, new int[]{237, 239, 255});
        kelvinTable.put(7500, new int[]{235, 238, 255});
        kelvinTable.put(7600, new int[]{233, 237, 255});
        kelvinTable.put(7700, new int[]{231, 236, 255});
        kelvinTable.put(7800, new int[]{230, 235, 255});
        kelvinTable.put(7900, new int[]{228, 234, 255});
        kelvinTable.put(8000, new int[]{227, 233, 255});
        kelvinTable.put(8100, new int[]{225, 232, 255});
        kelvinTable.put(8200, new int[]{224, 231, 255});
        kelvinTable.put(8300, new int[]{222, 230, 255});
        kelvinTable.put(8400, new int[]{221, 230, 255});
        kelvinTable.put(8500, new int[]{220, 229, 255});
        kelvinTable.put(8600, new int[]{218, 229, 255});
        kelvinTable.put(8700, new int[]{217, 227, 255});
        kelvinTable.put(8800, new int[]{216, 227, 255});
        kelvinTable.put(8900, new int[]{215, 226, 255});
        kelvinTable.put(9000, new int[]{214, 225, 255});
    }

    public static ColorRGB getRGBForKelvin(int kelvin) {
        Map.Entry<Integer, int[]> lower = kelvinTable.floorEntry(kelvin);
        Map.Entry<Integer, int[]> upper = kelvinTable.ceilingEntry(kelvin);

        if (lower == null || upper == null) {
            throw new IllegalArgumentException("Kelvin value out of range.");
        }

        if (lower.getKey().equals(upper.getKey())) {
            return new ColorRGB(lower.getValue()[0], lower.getValue()[1], lower.getValue()[2]);
        }

        int lowerKelvin = lower.getKey();
        int upperKelvin = upper.getKey();
        int[] lowerRGB = lower.getValue();
        int[] upperRGB = upper.getValue();

        float factor = (kelvin - lowerKelvin) / (float) (upperKelvin - lowerKelvin);

        int red = Math.round(lowerRGB[0] + factor * (upperRGB[0] - lowerRGB[0]));
        int green = Math.round(lowerRGB[1] + factor * (upperRGB[1] - lowerRGB[1]));
        int blue = Math.round(lowerRGB[2] + factor * (upperRGB[2] - lowerRGB[2]));

        return new ColorRGB(red, green, blue);
    }


}
