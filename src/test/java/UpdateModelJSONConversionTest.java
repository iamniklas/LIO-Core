import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class UpdateModelJSONConversionTest {
    @Test
    public void testEnumConversion() {
        String json = "{\"procedure\":\"BootComplete\",\"bundle\":{\"direction\":\"Left\"}}";

        LEDDataBundle ledDataBundleFromJson = new Gson().fromJson(json, LEDDataBundle.class);

        Assert.assertEquals(Direction.Left, ledDataBundleFromJson.direction);
    }
}
