package um.cps3230;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main (String [] args){
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","D:\\UNI\\Year 3 Semester 1\\Software Testing\\ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        PageScraper pg = new PageScraper(driver);
        RestApi api = new RestApi();
        final String id = "1460686c-0cda-452c-b8f7-7ca99211bf07";

        driver.get("https://www.amazon.de/Display-i7-11800H-Graphics-Keyboard-Windows/dp/B09T9Y4ZJ6/ref=sr_1_1_sspa?keywords=Gaming+laptop&qid=1668439226&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1");
        JSONObject js = pg.scrapePage();

        try {
            int upcode = api.upload(js);
        }catch (Exception e){}

        try {
            int delcode = api.deleteAll(id);
        }catch (Exception e){}


        int[] list = pg.CopyingAttributesOfFivePagesAndUploading();
        System.out.println("Number of listings: "+list.length);
    }
}
