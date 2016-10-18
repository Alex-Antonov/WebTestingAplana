package my.antonov.tests.impl;

import my.antonov.tests.AbstractTest;
import my.antonov.web.pages.ExtendedSearchGoodsPage;
import my.antonov.web.pages.MarketPage;
import my.antonov.web.pages.ResultOfSearchingPage;
import my.antonov.web.pages.YandexPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 17.10.2016.
 */
@Features("Testing goods searcher")
@RunWith(value = Parameterized.class)
public abstract class GoodsSearcherTest extends AbstractTest {

    private final static String CSV_DATA_PATH = "scenarios.csv";

    @Parameter("Тип искомого товара")
    protected String typeOfGood;

    @Parameter("Товар")
    protected String good;

    @Parameter("Стоимость от")
    protected String lowPrice;

    @Parameter("Фирмы производители")
    protected String[] modelsOfGood;


    public GoodsSearcherTest(String typeOfGood, String good,
                             String lowPrice, String modelsOfGood) {
        this.typeOfGood = typeOfGood;
        this.good = good;
        this.lowPrice = lowPrice;
        this.modelsOfGood = modelsOfGood.split(";");
    }

    public static Collection loadTestData(String operation) throws IOException {
        return getTestData(operation);
    }


    private static Collection<String[]> getTestData(String prefix)
            throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;
        try (
                InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CSV_DATA_PATH);
                InputStreamReader reader = new InputStreamReader(stream, "UTF8");
                BufferedReader br = new BufferedReader(reader);
        ) {
            while ((record = br.readLine()) != null) {
                String fields[] = record.split(";");
                if(prefix.equals(fields[0])) {
                    int modelCount = Integer.parseInt(fields[4]);
                    String models = "";
                    for(int i = 5; i < 5 + modelCount; i++) {
                        if(modelCount > 1 && i != modelCount + 4)
                            models += fields[i] + ";";
                        else
                            models += fields[i];
                    }
                    String[] params = {fields[1], fields[2], fields[3], models};
                    records.add(params);
                }

            }
            br.close();
            return records;
        } catch (IOException e) {
            throw  e;
        }
    }

    @Features("Test market searcher")
    @Description("Test equaling of first result of searching with parameters to result of searching by pruduct title")
    @Test
    public void testSearcher() {

        YandexPage yandexPage = new YandexPage(getDriver());
        MarketPage marketPage = yandexPage.goToMarketPage();
        ExtendedSearchGoodsPage extendedSearchGoodsPage = null;
        ResultOfSearchingPage resultOfSearchingPage = null;
        System.out.println(typeOfGood + " " + good);
        marketPage.selectTypeOfGood(typeOfGood);
        marketPage.selectGood(good);

        extendedSearchGoodsPage = marketPage.goToExtendedSearching();
        extendedSearchGoodsPage.setLowPriceInput(lowPrice);
        extendedSearchGoodsPage.selectModels(modelsOfGood);

        String firstResult = extendedSearchGoodsPage.getFirstResult();

        resultOfSearchingPage = extendedSearchGoodsPage.findByFirstResult(firstResult);
        assertTrue(resultOfSearchingPage.isSearchedEqualsToResult(firstResult));

    }
}
