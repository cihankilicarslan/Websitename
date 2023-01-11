package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void setUp() {
        //go to google home page
        driver = WebDriverUtil.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.bubilet.com.tr/izmir");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(12000, TimeUnit.MILLISECONDS);

    }
    @Test
    public void TC1_Bubilet() throws InterruptedException {


        // Hashtable<String,String> ht= new Hashtable<String, String>();
        Map<String, String> hm = new <String, ArrayList<String>>HashMap();
        List<String> list = new  ArrayList<>();
        Vector<String> v=new Vector<>();

        List<WebElement> EventT = driver.findElements(By.xpath("//h3[@class='event-title']//a"));
        List<WebElement> EventL = driver.findElements(By.xpath("//ul[@class='event-info']//li[1]"));
        List<WebElement> EventD = driver.findElements(By.xpath("//ul[@class='event-info']//li[2]"));
        List<WebElement> EventP = driver.findElements(By.xpath("//div[@class='event-price']"));
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        Map<String, String> treemap = new TreeMap<>();
        int i=0;
        for (WebElement event:EventT) {

            Thread.sleep(200);
            hm.put("Tittle",event.getText());
            hm.put("Location",EventL.get(i).getText());
            hm.put("Date",EventD.get(i).getText());
            hm.put("Price",EventP.get(i).getText());

            list.add("{Tittle:");
            list.add(EventT.get(i).getText());
            list.add( "Location:");
            list.add(EventL.get(i).getText());
            list.add( "Date:");
            list.add(EventD.get(i).getText());
            list.add( "Price:");
            list.add(EventP.get(i).getText());
            list.add( "}");


        }
        //Inserting key-value pairs into the json object
        jsonObject.put("izmir",list);



        try {
            FileWriter file = new FileWriter("output.json");
            file.write(String.valueOf(jsonObject));
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert the list of elements to JSON and write to a file
            mapper.writeValue(new File("elements.json"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
        System.out.println("Current list             : " + list);
        System.out.println("Current list hm          : " + hm);
        // Print the TreeMap
        //  System.out.println(treemap.values());

    }


    @AfterMethod
    public void closing() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();

    }

}
