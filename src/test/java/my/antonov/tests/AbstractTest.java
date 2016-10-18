package my.antonov.tests;

import my.antonov.util.TestingProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by alex on 17.10.2016.
 */
public abstract class AbstractTest implements IBrowsers {

    private WebDriver driver;

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void init() {
        setUpDriver();
        driver.manage().window().maximize();
        driver.navigate().to(TestingProperties.URL);
    }

    private void setUpDriver() {
        DesiredCapabilities capabilities = null;
        switch(TestingProperties.BROWSER) {
            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("javascript", true);
                driver = new FirefoxDriver(capabilities);
                break;
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("javascript", true);
                driver = new ChromeDriver(capabilities);
                break;
            case EDGE:
                capabilities = DesiredCapabilities.edge();
                capabilities.setCapability("javascript", true);
                driver = new EdgeDriver(capabilities);
                break;
        }
    }

    @Rule
    public TestWatcher screenshot = new TestWatcher() {

        @Override
        protected void succeeded(Description description) {
            makeScreenshot();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshot();
        }

        @Attachment(type = "image/png")
        public byte[] makeScreenshot() {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
    };

}
