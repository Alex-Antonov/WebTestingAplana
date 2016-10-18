package my.antonov.tests.impl.searching;

import my.antonov.tests.impl.GoodsSearcherTest;
import my.antonov.web.pages.ExtendedSearchGoodsPage;
import my.antonov.web.pages.MarketPage;
import my.antonov.web.pages.ResultOfSearchingPage;
import my.antonov.web.pages.YandexPage;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.SearchContext;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 17.10.2016.
 */
public class TvSearcherTest extends GoodsSearcherTest {


    public TvSearcherTest(String typeOfGood, String good, String lowPrice, String modelsOfGood) {
        super(typeOfGood, good, lowPrice, modelsOfGood);
    }



    @Parameterized.Parameters(name = "Search in {0} {1} at low price {2} by models {3}")
    public static Collection getTestData() throws IOException {
        return loadTestData("tv");
    }
}
