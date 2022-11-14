package test.um.cps3230.Cucumber.Files;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import um.cps3230.Task2;

import java.util.List;

public class Task2Steps {
    Task2 task;
    WebDriver driver;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webdriver.chrome.driver", "D:\\UNI\\Year 3 Semester 1\\Software Testing\\ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.marketalertum.com/Alerts/Login");
        task = new Task2(driver);
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        task.Login();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", url);
        driver.quit();
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        task.invalidLogin();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login",url);
        driver.quit();
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        task  = new Task2(driver);
        task.uploadListings(arg0);
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        driver.get("https://www.marketalertum.com/Alerts/Login");
        task.Login();
        driver.navigate().refresh();
        List<WebElement> lists = driver.findElements(By.xpath("//table[@border='1']"));
        Assertions.assertNotEquals(0, lists.size());
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        String icon = driver.findElement(By.xpath("//h4/img[@width='100']")).getAttribute("src");
        System.out.println("Icon: "+icon);
        Assertions.assertNotNull(icon);
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        String heading = driver.findElement(By.cssSelector("h4")).getText();
        System.out.println("Heading: "+heading);
        Assertions.assertNotNull(heading);
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        String desc = driver.findElement(By.xpath("//tbody/tr[3]/td")).getText();
        System.out.println("Description: "+desc);
        Assertions.assertNotNull(desc);
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        String img = driver.findElement(By.xpath("//td[@rowspan='4']/img[@width='200']")).getAttribute("src");
        System.out.println("Image: "+img);
        Assertions.assertNotNull(img);
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> tds =  driver.findElements(By.cssSelector("td"));
        String price = null;
        for(WebElement elem : tds){
            if(elem.getText().contains("€")){
                price = elem.getText();
            }
        }
        System.out.println("Price: "+price);
        Assertions.assertNotNull(price);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        String link = driver.findElement(By.xpath("//tbody/tr[5]/td/a")).getAttribute("href");
        System.out.println("Link: "+link);
        Assertions.assertNotNull(link);
        driver.quit();
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        task = new Task2(driver);
        task.uploadListings(arg0+1);
    }

    @Given("I am an administrator of the website and I upload an alert of type {string}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(String arg0) {
        int type = Integer.parseInt(arg0);
        task = new Task2(driver);
        task.uploadListingType(type);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        /*driver.get("https://www.marketalertum.com/Alerts/Login");
        task.Login();*/
        List<WebElement> lists = driver.findElements(By.xpath("//table[@border='1']"));
        System.out.println("Listings: "+lists.size());
        Assertions.assertEquals(arg0, lists.size());

    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        String src = driver.findElement(By.xpath("//h4/img[@width='100']")).getAttribute("src");
        String[] name = src.split("/");
        System.out.println("Name: " + name[4]);
        Assertions.assertEquals(arg0,name[4]);
        driver.quit();
    }

}
