package um.cps3230;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import java.io.IOException;

public class RestApi {
    public int upload(JSONObject json) throws Exception{
        //Setting up Http connection
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse hr;
        //Post Request to add Listing
        try{
            HttpPost req = new HttpPost("https://api.marketalertum.com/Alert");
            req.addHeader("content-type","application/json");
            StringEntity parameters = new StringEntity(json.toString());
            req.setEntity(parameters);
            System.out.println(json.toString());
            hr = httpClient.execute(req);
            //Returning response code to make sure request was accepted
            System.out.println("Response Code: "+hr.getCode());
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //Closing http connection
            httpClient.close();
        }
        return hr.getCode();
    }

    public int deleteAll(String id) throws IOException{
        CloseableHttpClient httpClientD = HttpClientBuilder.create().build();
        int code = 0;
        try{
            //Delete request to purge all alerts
            String link = "https://api.marketalertum.com/Alert?userId=".concat(id);
            HttpDelete del = new HttpDelete(link);
            HttpResponse d = httpClientD.execute(del);
            code = d.getCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            httpClientD.close();
        }
        return code;
    }
}