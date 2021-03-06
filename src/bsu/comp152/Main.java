package bsu.comp152;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
	    var dataGrabber = HttpClient.newHttpClient();
        var requestMarker = HttpRequest.newBuilder();
        var webAddress = URI.create("http://universities.hipolabs.com/search?name=Young");
        var dataRequest = requestMarker.uri(webAddress).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException exception){
            System.out.println("Error connecting to network resource");
        }
        catch (InterruptedException e){
            System.out.println("An error occurred getting a response from the server");
        }
        if(response == null){
            System.out.println("Something went terribly wrong - we have to close now");
            System.exit(-1);
        }
        var usefulData = response.body();
        System.out.println(usefulData);
        var dataParser = new Gson();
    }
}
