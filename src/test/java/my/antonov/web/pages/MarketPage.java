package my.antonov.web.pages;

import my.antonov.web.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 17.10.2016.
 */
public class MarketPage extends AbstractPage {

    public MarketPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select type of good {0}")
    public void selectTypeOfGood(String typeOfGood) {
        WebElement elementTypeOfGood =
                getDriver().findElement(
                    By.xpath(".//ul[@class='topmenu__list']/li[@data-department='" + typeOfGood + "']/a")
                );

        elementTypeOfGood.click();
    }

    @Step("Select good {0}")
    public void selectGood(String good) {
        WebElement elementGood =
                getDriver().findElement(
                    By.xpath(".//div[@class='catalog-menu__list']/a[text()='" + good + "']")
                );

        elementGood.click();
    }

    @Step("Going to extended searching page")
    public ExtendedSearchGoodsPage goToExtendedSearching() {
        WebElement exSearchLink =
                getDriver().findElement(
                        By.xpath(".//tr/td[@class='more']/a")
                );
        exSearchLink.click();
        return new ExtendedSearchGoodsPage(getDriver());
    }
}
