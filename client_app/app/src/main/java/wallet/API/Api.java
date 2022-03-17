package wallet.API;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Api {

    private HttpClient           client   = HttpClient.newHttpClient();
    private HttpRequest          request;
    private HttpResponse<String> response;

    public String get_user(String userID) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/user/"+userID)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public String get_wallets(String userID) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/wallets/"+userID)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

}
