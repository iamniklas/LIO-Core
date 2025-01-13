import com.github.iamniklas.colorspaces.ColorHSV;
import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.colorspaces.ColorRGBA;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorSpaceConversionTests {
    @Test
    public void testHSVToRGBConversion() {
        ColorHSV hsv = new ColorHSV(0, 1.0f, 1.0f);
        assertEquals(255, hsv.toRGB().getR());
        assertEquals(0, hsv.toRGB().getG());
        assertEquals(0, hsv.toRGB().getB());

        hsv.setH(60);
        assertEquals(255, hsv.toRGB().getR());
        assertEquals(255, hsv.toRGB().getG());
        assertEquals(0, hsv.toRGB().getB());

        hsv.setH(120);
        assertEquals(0, hsv.toRGB().getR());
        assertEquals(255, hsv.toRGB().getG());
        assertEquals(0, hsv.toRGB().getB());

        hsv.setH(180);
        assertEquals(0, hsv.toRGB().getR());
        assertEquals(255, hsv.toRGB().getG());
        assertEquals(255, hsv.toRGB().getB());

        hsv.setH(240);
        assertEquals(0, hsv.toRGB().getR());
        assertEquals(0, hsv.toRGB().getG());
        assertEquals(255, hsv.toRGB().getB());

        hsv.setH(300);
        assertEquals(255, hsv.toRGB().getR());
        assertEquals(0, hsv.toRGB().getG());
        assertEquals(255, hsv.toRGB().getB());

        hsv.setH(360);
        assertEquals(255, hsv.toRGB().getR());
        assertEquals(0, hsv.toRGB().getG());
        assertEquals(0, hsv.toRGB().getB());
    }

    @Test
    public void testRGBToHSVConversion() {
        ColorRGB rgb = new ColorRGB(255, 0, 0);
        assertEquals(0, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(255);
        rgb.setG(255);
        rgb.setB(0);
        assertEquals(60, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(0);
        rgb.setG(255);
        rgb.setB(0);
        assertEquals(120, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(0);
        rgb.setG(255);
        rgb.setB(255);
        assertEquals(180, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(0);
        rgb.setG(0);
        rgb.setB(255);
        assertEquals(240, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(255);
        rgb.setG(0);
        rgb.setB(255);
        assertEquals(300, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);

        rgb.setR(255);
        rgb.setG(0);
        rgb.setB(0);
        assertEquals(0, rgb.toHSV().getH());
        assertEquals(1.0f, rgb.toHSV().getS(), 0.1f);
        assertEquals(1.0f, rgb.toHSV().getV(), 0.1f);
    }

    @Test
    public void testRGBAToRGBConversion() {
        ColorRGBA rgba = new ColorRGBA(255, 255, 255, 128);
        ColorRGB base = new ColorRGB(128, 0, 0);

        ColorRGB mix = rgba.toRGB(base);

        System.out.println(mix);
    }
}
