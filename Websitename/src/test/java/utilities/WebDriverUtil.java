package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtil {
    //create a util that get the browser and create connection btw browser and selenium
    //WebDriverUtil will set up the browser for us

    public static WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            //chrome browser needs to be setup
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else {
            System.out.println("Browser that you provide is not exist!");
            System.out.println("browser that provided= "+browser);
            return null;
        }
    }
}
