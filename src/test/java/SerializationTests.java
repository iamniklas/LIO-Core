import com.github.iamniklas.liocore.led.LEDRenderer;
import com.github.iamniklas.liocore.led.LEDStripManager;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.ProcedureType;
import com.google.gson.Gson;
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


        com.github.iamniklas.liocore.led.LEDDataBundle bundle = new com.github.iamniklas.liocore.led.LEDDataBundle();
        bundle.ledStrip = ledStripManager;
        bundle.procedureCalls = ledStripManager;
        bundle.speed = 5.0f;

        LEDUpdateModel u = new LEDUpdateModel();
        u.bundle = bundle;
        u.procedure = ProcedureType.Rainbow;

        System.out.println(new Gson().toJson(u));
    }
}
