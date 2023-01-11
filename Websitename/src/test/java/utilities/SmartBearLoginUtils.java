package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SmartBearLoginUtils {

    public static void loginForSmartBear(WebDriver driver){
        //3- Enter username --->Tester
        //4- Enter password --->test
        //5- Click “Login” button
        WebElement userName=driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");
        WebElement password=driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("test");
        WebElement loginButton=driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();
    }

    public static void negativeLoginForSmartBear(WebDriver driver,String userID,String passwordID){

        WebElement userName=driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys(userID);
        WebElement password=driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys(passwordID);
        WebElement loginButton=driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();

    }

    public static void verifyStreet(WebDriver driver,String streetName){
        //to locate all table //table[@id='ctl00_MainContent_orderGrid']
        //to locate all table rows //table[@id='ctl00_MainContent_orderGrid']//tr
        //to locate 5.row //table[@id='ctl00_MainContent_orderGrid']//tr[5]
        //to locate all streets //table[@id='ctl00_MainContent_orderGrid']//tr//td[6]
        List<WebElement> streetList=driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tr//td[6]"));

        for(WebElement eachStreet:streetList){
            if(eachStreet.getText().equals(streetName)){
                Assert.assertTrue(eachStreet.getText().equals(streetName));
                return;
            }
        }
    Assert.fail("Street name: "+streetName+" is not present on the table");
    }

}
