package um.cps3230;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import um.cps3230.Interfaces.Website;

import java.util.List;

public class PageScraper {
    WebDriver driver;
    AmazonPageObjects apo;
    RestApi api = new RestApi();
    protected Website website;
    final String id = "1460686c-0cda-452c-b8f7-7ca99211bf07";

    public PageScraper(WebDriver driver){
        this.driver = driver;
    }

    public JSONObject scrapePage(){
        apo = new AmazonPageObjects(driver);

        int alertType = 6;
        String title = apo.getTitle();
        String heading = trim(title);
        String url = driver.getCurrentUrl();
        String imageUrl = apo.getImageUrl();
        String postedBy = id;
        String price = apo.getPrice();

        System.out.println("id: "+id);
        System.out.println("alertType: "+alertType);
        System.out.println("heading: "+heading);
        System.out.println("description: "+title);
        System.out.println("url: "+url);
        System.out.println("imageURL: "+imageUrl);
        System.out.println("postedBy: "+postedBy);
        System.out.println("priceInCents: "+price);
        System.out.println();

        JSONObject obj = new JSONObject();
        obj.put("userId", id);
        obj.put("alertType", alertType);
        obj.put("heading", heading);
        obj.put("description", title);
        obj.put("url", url);
        obj.put("imageUrl", imageUrl);
        obj.put("postedBy", postedBy);
        obj.put("priceInCents", price);

        return obj;
    }

    public int[] CopyingAttributesOfFivePagesAndUploading(){
        System.setProperty("webdriver.chrome.driver","D:\\UNI\\Year 3 Semester 1\\Software Testing\\ChromeDriver/chromedriver.exe");
        int pages = 0;
        int[] codes = new int[5];
        if(website!=null && website.getStatus()==Website.online) {
            driver.get("https://www.amazon.de");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Gaming laptop" + Keys.ENTER);
            //Accepting Amazon's cookies
            driver.findElement(By.xpath("//input[@id='sp-cc-accept']")).click();

            List<WebElement> elem = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
            JSONObject js[] = new JSONObject[5];
            for (int i = 0; i < 5; i++) {
                if (i == 0) {
                    elem.get(i).click();
                    js[i] = scrapePage();
                    try {
                        int result = api.upload(js[i]);
                        codes[i] = result;
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    driver.navigate().back();
                    pages++;
                } else {
                    if (driver.findElements(By.xpath("//input[@id='sp-cc-accept']")).size() > 0) {
                        driver.findElement(By.xpath("//input[@id='sp-cc-accept']")).click();
                    }
                    elem = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
                    elem.get(i).click();
                    js[i] = scrapePage();
                    try {
                        int result = api.upload(js[i]);
                        codes[i] = result;
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    driver.navigate().back();
                    pages++;
                }
            }
            driver.quit();
        }
        else{
            codes[0]=0;
        }
        return codes;
    }

    public String trim(String text){
        String[] trims = text.split(",|\\|");
        String trimmed = trims[0];
        return trimmed;
    }

    public void setWebsiteStatus(Website website){
        this.website = website;
    }
}
