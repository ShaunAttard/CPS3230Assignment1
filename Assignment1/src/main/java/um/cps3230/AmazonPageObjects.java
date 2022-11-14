package um.cps3230;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonPageObjects {

    WebDriver driver;

    public AmazonPageObjects(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        String title = driver.findElement(By.id("productTitle")).getText();
        return title;
    }

    public String getImageUrl(){
        String imageUrl = driver.findElement(By.xpath("//div[@id = 'imgTagWrapperId']/img[@data-a-image-name= 'landingImage']")).getAttribute("src");
        return imageUrl;
    }

    public String getPrice(){
        String price = "";
        List<WebElement> el = driver.findElements(By.xpath("//span[@class = 'a-price a-text-price a-size-medium apexPriceToPay']"));
        if (el.size()==1) {
            price = driver.findElement(By.xpath("//span[@class = 'a-price a-text-price a-size-medium apexPriceToPay']")).getText();
            price = format(price);
        } else {
            price = driver.findElement(By.xpath("//span[@class = 'a-price-whole']")).getText();
            price = format(price).concat("00");
        }
        return price;
    }

    public String format(String price){
        price = price.replace(",","");
        price = price.replace(".","");
        price = price.replace("€","");
        return price;
    }
}
