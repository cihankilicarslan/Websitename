package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReadingUtil {

    private static Properties properties=new Properties();
    //encapsulation method, we hide data

    static {
        String pathForPropertiesFile="configuration.properties";

        try {
            //tell compiler to open the file
            FileInputStream fileInputStream=new FileInputStream(pathForPropertiesFile);
            //file need to be load in framework
            properties.load(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Properties file not found!!!");
        }

    }

    public static String getProperties(String keyword){
        return properties.getProperty(keyword);
    }



}
