package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.ClientStats;
import utilities.WebDriverUtil;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;




public class bubiletData {

    WebDriver driver;


    @BeforeMethod
    public void setUp() throws InterruptedException {
        //go to google home page
        driver = WebDriverUtil.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.bubilet.com.tr/bolu");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(12000, TimeUnit.MILLISECONDS);

    }
    @Test
    public void TC1_Bubilet() throws InterruptedException {
     //  List<WebElement> bTags = driver.findElements(By.cssSelector("div.select-city-right.scroll ul li a label b"));

        List<String> list = new ArrayList<>();
        List<WebElement> EventT = driver.findElements(By.xpath("//h3[@class='event-title']//a"));
        List<WebElement> EventL = driver.findElements(By.xpath("//ul[@class='event-info']//li[1]"));
        List<WebElement> EventD = driver.findElements(By.xpath("//ul[@class='event-info']//li[2]"));
        List<WebElement> EventP = driver.findElements(By.xpath("//div[@class='event-price']"));
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();

        int i = 0;

        for (WebElement e : EventT) {

            list.add("Tittle:" + EventT.get(i).getText());
            list.add("Location:" + EventL.get(i).getText());
            list.add("Date:" + EventD.get(i).getText());
            list.add("Price:" + EventP.get(i).getText());

            i++;
        }
        //mapper tech
        ObjectMapper mapper = new ObjectMapper();

        try {

            mapper.writeValue(new File("elements.json"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // object tech
        jsonObject.put(String.valueOf(list.size()),list);
        try {
            FileWriter file = new FileWriter("output.json");
            file.write(String.valueOf(jsonObject));
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @AfterMethod
    public void closing() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();

    }

}
