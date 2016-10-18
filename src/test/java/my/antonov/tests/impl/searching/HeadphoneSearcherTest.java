package my.antonov.tests.impl.searching;

import my.antonov.tests.impl.GoodsSearcherTest;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by alex on 18.10.2016.
 */
public class HeadphoneSearcherTest extends GoodsSearcherTest {

    public HeadphoneSearcherTest(String typeOfGood, String good, String lowPrice, String modelsOfGood) {
        super(typeOfGood, good, lowPrice, modelsOfGood);
    }

    @Parameterized.Parameters(name = "Search in {0} {1} at low price {2} by models {3}")
    public static Collection getTestData() throws IOException {
        return loadTestData("hp");
    }
}
