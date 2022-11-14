package um.cps3230;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Task2 {
    WebDriver driver;
    RestApi api;
    final String id = "1460686c-0cda-452c-b8f7-7ca99211bf07";

    public Task2(WebDriver driver){
        this.driver = driver;
    }

    public void Login(){
        driver.findElement(By.id("UserId")).sendKeys(id);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void invalidLogin(){
        driver.findElement(By.id("UserId")).sendKeys("blablabla");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void uploadListings(int rep){
        api = new RestApi();
        try {
            int del = api.deleteAll(id);
        } catch (IOException e) {
            System.out.println("Not working");
        }
        JSONObject obj = new JSONObject();
        obj.put("userId", id);
        obj.put("alertType", 6);
        obj.put("heading", "Jumper Windows 11 Laptop");
        obj.put("description", "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        obj.put("url", "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        obj.put("imageUrl", "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        obj.put("postedBy", id);
        obj.put("priceInCents", 24999);
        for(int i=0; i<rep; i++) {
            try {
                int result = api.upload(obj);
            } catch (Exception e) {
                System.out.println("No Upload");
                System.out.println(e.toString());
            }
        }
    }

    public void uploadListingType(int type){
        api = new RestApi();
        try {
            int del = api.deleteAll(id);
        } catch (IOException e) {
            System.out.println("Not working");
        }
        JSONObject obj = new JSONObject();
        obj.put("userId", id);
        obj.put("alertType", type);
        obj.put("heading", "Test Listing");
        obj.put("description", "Listing to check icon");
        obj.put("url", "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        obj.put("imageUrl", "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        obj.put("postedBy", id);
        obj.put("priceInCents", 24999);
            try {
                int result = api.upload(obj);
            } catch (Exception e) {
                System.out.println("No Upload");
                System.out.println(e.toString());
            }
    }
}
