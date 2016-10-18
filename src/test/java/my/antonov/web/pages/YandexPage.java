package my.antonov.web.pages;

import my.antonov.web.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by alex on 17.10.2016.
 */
public class YandexPage extends AbstractPage{

    @FindBy(xpath = ".//div[@class='home-arrow']/div[1]/div/a[2]")
    @CacheLookup
    private WebElement marketTab;

    public YandexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Go to yandex market page")
    public MarketPage goToMarketPage() {
        marketTab.click();
        return new MarketPage(getDriver());
    }
}
