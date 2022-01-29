import com.github.iamniklas.liocore.network.LEDUpdateModel;
import com.github.iamniklas.liocore.procedures.models.Direction;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class UpdateModelJSONConversionTest {
    @Test
    public void testEnumConversion() {
        String json = "{\"procedure\":\"BootComplete\",\"bundle\":{\"direction\":\"Left\"}}";

        LEDUpdateModel ledUpdateModelFromJson = new Gson().fromJson(json, LEDUpdateModel.class);

        Assert.assertEquals(Direction.Left, ledUpdateModelFromJson.bundle.direction);
    }
}
