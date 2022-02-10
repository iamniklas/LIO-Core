import com.github.iamniklas.liocore.led.colorspace.ColorHSV;
import com.github.iamniklas.liocore.led.colorspace.ColorRGB;
import com.github.iamniklas.liocore.led.colorspace.ColorRGBA;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColorSpaceConversionTests {
    @Test
    public void testHSVToRGBConversion() {
        ColorHSV hsv = new ColorHSV(0, 1.0f, 1.0f);
        assertEquals(255, hsv.toRGB().r);
        assertEquals(0, hsv.toRGB().g);
        assertEquals(0, hsv.toRGB().b);

        hsv.h = 60;
        assertEquals(255, hsv.toRGB().r);
        assertEquals(255, hsv.toRGB().g);
        assertEquals(0, hsv.toRGB().b);

        hsv.h = 120;
        assertEquals(0, hsv.toRGB().r);
        assertEquals(255, hsv.toRGB().g);
        assertEquals(0, hsv.toRGB().b);

        hsv.h = 180;
        assertEquals(0, hsv.toRGB().r);
        assertEquals(255, hsv.toRGB().g);
        assertEquals(255, hsv.toRGB().b);

        hsv.h = 240;
        assertEquals(0, hsv.toRGB().r);
        assertEquals(0, hsv.toRGB().g);
        assertEquals(255, hsv.toRGB().b);

        hsv.h = 300;
        assertEquals(255, hsv.toRGB().r);
        assertEquals(0, hsv.toRGB().g);
        assertEquals(255, hsv.toRGB().b);

        hsv.h = 360;
        assertEquals(255, hsv.toRGB().r);
        assertEquals(0, hsv.toRGB().g);
        assertEquals(0, hsv.toRGB().b);
    }

    @Test
    public void testRGBToHSVConversion() {
        ColorRGB rgb = new ColorRGB(255, 0, 0);
        assertEquals(0, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 255;
        rgb.g = 255;
        rgb.b = 0;
        assertEquals(60, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 0;
        rgb.g = 255;
        rgb.b = 0;
        assertEquals(120, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 0;
        rgb.g = 255;
        rgb.b = 255;
        assertEquals(180, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 0;
        rgb.g = 0;
        rgb.b = 255;
        assertEquals(240, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 255;
        rgb.g = 0;
        rgb.b = 255;
        assertEquals(300, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);

        rgb.r = 255;
        rgb.g = 0;
        rgb.b = 0;
        assertEquals(0, rgb.toHSV().h);
        assertEquals(1.0f, rgb.toHSV().s, 0.1f);
        assertEquals(1.0f, rgb.toHSV().v, 0.1f);
    }

    @Test
    public void testRGBAToRGBConversion() {
        ColorRGBA rgba = new ColorRGBA(255, 255, 255, 128);
        ColorRGB base = new ColorRGB(128, 0, 0);

        ColorRGB mix = rgba.toRGB(base);

        System.out.println(mix);
    }
}
