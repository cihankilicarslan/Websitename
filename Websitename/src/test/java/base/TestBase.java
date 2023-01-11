package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.BrowserUtils;
import utilities.WebDriverUtil;

import java.util.concurrent.TimeUnit;

public class TestBase {
//using access modifier
   protected WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver= WebDriverUtil.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @AfterMethod()
    public void closing(){
        BrowserUtils.staticWait(5);
        //driver.close();
    }
}
