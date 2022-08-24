import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.ProcedureType;
import com.github.iamniklas.liocore.procedures.variants.RainbowProcedure;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SerializationTests {
    @Test
    public void testProcedureSerializer() {
        LEDRenderer renderer = new LEDRenderer() {
            @Override
            public void render(ArrayList<LIOColor> _colorData) {

            }
        };
        LEDStripManager ledStripManager = new LEDStripManager(renderer, 300);


        LEDDataBundle bundle = new LEDDataBundle();
        bundle.ledStrip = ledStripManager;
        bundle.procedureCalls = ledStripManager;
        bundle.speed = 5.0f;

        LEDUpdateModel u = new LEDUpdateModel();
        u.bundle = bundle;
        u.procedure = ProcedureType.Rainbow;

        System.out.println(new Gson().toJson(u));
    }
}
