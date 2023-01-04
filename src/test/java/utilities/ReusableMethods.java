package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class ReusableMethods {
    static Actions actions = new Actions(Driver.getDriver());
    public static String getScreenshot(String name) throws IOException {
       String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
       TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
       String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        return target;
    }
    public static Map<String, Integer> collectInfo(){
       int rows = Driver.getDriver().findElements(By.tagName("tr")).size();
       Map<String, Integer> result = new HashMap<>();
       for (int i = 2; i<=rows; i++)
       {
           result.put(names(i), prices(i));
       }
       return result;
    }
    public static String names (int tr){
        String name = "";
        try{
            actions.moveToElement(Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[3]/div/a/div/div/p"))).perform();
            name = Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[3]/div/a/div/div/p")).getText();
        }catch (Exception e){
            Driver.wait(2);
            actions.moveToElement(Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[3]/div/a/div/div/p"))).perform();
            name = Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[3]/div/a/div/div/p")).getText();
        }
        return name;
    }
    public static int prices (int tr){
        String price = "";
        try {
            actions.moveToElement(Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[4]/div/a/span"))).perform();
            price = Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[4]/div/a/span")).getText().replaceAll("[^0-9]", "");

        }catch (Exception e){
            Driver.wait(2);
            actions.moveToElement(Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[4]/div/a/span"))).perform();
            price = Driver.getDriver().findElement(By.xpath("(//tr)["+tr+"]//td[4]/div/a/span")).getText().replaceAll("[^0-9]", "");
        }
        return Integer.parseInt(price);
    }
    }