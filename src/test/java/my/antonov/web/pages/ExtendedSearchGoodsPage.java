package my.antonov.web.pages;

import my.antonov.web.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 18.10.2016.
 */
public class ExtendedSearchGoodsPage extends AbstractPage {


    public ExtendedSearchGoodsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Setting low price {0}")
    public void setLowPriceInput(String lowPrice) {
        WebElement lowPriceInput = getDriver().findElement(
                By.xpath(".//span[@class='input__box']/input[@id='glf-pricefrom-var']")
        );
        lowPriceInput.clear();
        lowPriceInput.sendKeys(lowPrice);
    }

    @Step("Select models {0}")
    public void selectModels(String[] models) {

        for(String m : models) {
            WebElement modelCheckBoxGroup = getDriver().findElement(
                    By.xpath(".//span/label[text()='" + m + "']")
            );
            modelCheckBoxGroup.click();
        }

        //не знаю к какому элементу применить wait until
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getFirstResult() {


        WebElement firstResultElement =
                //getDriver().findElement(By.xpath(".//div/div[1]/div[3]/div/div[1]/div/h3/a/span"));
                getDriver().findElement(By.xpath(".//h3/a/span"));

        return firstResultElement.getText();
    }

    public ResultOfSearchingPage findByFirstResult(String firstResult) {

        WebElement marketInputSearch = getDriver().findElement(By.xpath(".//span[@class='input__box']/input"));
        marketInputSearch.sendKeys(firstResult);
        marketInputSearch.submit();
        return new ResultOfSearchingPage(getDriver());
    }
}
