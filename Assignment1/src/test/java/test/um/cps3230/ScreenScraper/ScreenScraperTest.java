package test.um.cps3230.ScreenScraper;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import um.cps3230.Interfaces.Website;
import um.cps3230.PageScraper;
import um.cps3230.RestApi;

public class ScreenScraperTest {
    RestApi api;
    PageScraper scrape;
    Website website;
    final int[] accepted = {201,201,201,201,201};
    final String id = "1460686c-0cda-452c-b8f7-7ca99211bf07";

    @BeforeEach
    public void setup(){
        api = Mockito.mock(RestApi.class);
        scrape = Mockito.mock(PageScraper.class);
        website = Mockito.mock(Website.class);
    }

    @Test
    public void TestUploadRequest(){
        //Setup
        // Creating dummy data to pass
        JSONObject dummy = new JSONObject();
        dummy.put("test", "test");
        try {
            Mockito.when(api.upload(dummy)).thenReturn(201);
        }catch (Exception e){}

        //Exercise
        int code = 0;
        try {
            code = api.upload(dummy);
        }catch (Exception e){}
        System.out.println("Code: "+ code);

        //Verify
        //Comparing Request response code to 201 expected code
        Assertions.assertEquals(201,code);
    }

    @Test
    public void TestUploadInvalidRequest(){
        //Setup
        //Creating dummy data to pass
        JSONObject dummy = new JSONObject();
        dummy.put("test", "test");
        try {
            Mockito.when(api.upload(dummy)).thenReturn(400);
        }catch (Exception e){}

        //Exercise
        int code = 0;
        try {
            code = api.upload(dummy);
        }catch (Exception e){}
        System.out.println("Code: "+ code);

        //Verify
        Assertions.assertEquals(400,code);
    }

    @Test
    public void TestWebsiteOnline(){
        //Assuming RestApi is working correctly
        //Setup
        //Setting website's status as online
        Mockito.when(website.getStatus()).thenReturn(website.online);
        int[] valid = {201,201,201,201,201};

        Mockito.when(scrape.CopyingAttributesOfFivePagesAndUploading()).thenReturn(valid);

        //Exercise
        //Since website is online requests will carry out as usual resulting in 201 response codes
        scrape.setWebsiteStatus(website);
        int[] codes = scrape.CopyingAttributesOfFivePagesAndUploading();
        System.out.println("Codes: "+codes[0]+" "+codes[1]+" "+codes[2]+" "+codes[3]+" "+codes[4]);

        //Verify
        Assertions.assertArrayEquals(codes, accepted);
    }

    @Test
    public void TestWebsiteOffline(){
        //Setup
        int[] off = {0,0,0,0,0};
        Mockito.when(website.getStatus()).thenReturn(website.offline);
        Mockito.when(scrape.CopyingAttributesOfFivePagesAndUploading()).thenReturn(off);

        //Exercise
        //Since website is offline the ScreenScraper method will return 0's
        scrape.setWebsiteStatus(website);
        int[] codes = scrape.CopyingAttributesOfFivePagesAndUploading();
        System.out.println("Code: "+codes[0]);

        //Verify
        Assertions.assertArrayEquals(off, codes);
    }

    @Test
    public void TestValidScreenScraperRequests(){
        //Assuming Website is online and RestApi is working correctly
        //Setup
        int[] valid = {201,201,201,201,201};
        Mockito.when(website.getStatus()).thenReturn(website.online);
        Mockito.when(scrape.CopyingAttributesOfFivePagesAndUploading()).thenReturn(valid);

        //Exercise
        //Running the ScreenScraper with valid requests resulting in 201 response codes
        scrape.setWebsiteStatus(website);
        int[] codes = scrape.CopyingAttributesOfFivePagesAndUploading();
        System.out.println("Codes: "+codes[0]+" "+codes[1]+" "+codes[2]+" "+codes[3]+" "+codes[4]);

        //Verify
        Assertions.assertArrayEquals(codes, accepted);
    }

    @Test
    public void TestInvalidScreenScraperRequests(){
        //Assuming Website is online and RestApi is working correctly
        //Setup
        int[] valid = {201,201,201,400,201};
        Mockito.when(website.getStatus()).thenReturn(website.online);
        Mockito.when(scrape.CopyingAttributesOfFivePagesAndUploading()).thenReturn(valid);

        //Exercise
        //Running the ScreenScraper with valid requests resulting in 201 response codes
        scrape.setWebsiteStatus(website);
        int[] codes = scrape.CopyingAttributesOfFivePagesAndUploading();
        System.out.println("Codes: "+codes[0]+" "+codes[1]+" "+codes[2]+" "+codes[3]+" "+codes[4]);

        //Verify
        Assertions.assertNotEquals(codes, accepted);
    }

    @Test
    public void TestValidDeleteRequest(){
        //Setup
        try {
            Mockito.when(api.deleteAll(id)).thenReturn(200);
        }catch (Exception e){}

        //Exercise
        //Sending delete request with correct id results in 200 response code
        int code = 0;
        try {
            code = api.deleteAll(id);
        }catch (Exception e){}
        System.out.println("Code: "+code);

        //Verify
        Assertions.assertEquals(200,code);
    }

    @Test
    public void TestInvalidDeleteRequest(){
        //Setup
        try {
            Mockito.when(api.deleteAll("5236")).thenReturn(400);
        }catch (Exception e){}

        //Exercise
        //Sending the delete request with an incorrect id results in a 400 response code
        int code = 0;
        try {
            code = api.deleteAll("5236");
        }catch (Exception e){}
        System.out.println("Code: "+code);

        //Verify
        Assertions.assertEquals(400,code);
    }

}
