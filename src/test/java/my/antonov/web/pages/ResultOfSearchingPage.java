package my.antonov.web.pages;

import my.antonov.web.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by alex on 18.10.2016.
 */
public class ResultOfSearchingPage extends AbstractPage {

    //@FindBy(xpath = ".//div[@class='n-product-title']/h1")
    private WebElement productTitle;

    public ResultOfSearchingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checking the result {0} is equals title of product")
    public boolean isSearchedEqualsToResult(String result) {
        productTitle = getDriver().findElement(By.xpath(".//div[@class='n-product-title']/h1"));
        return result.equals(productTitle.getText());
    }
}
