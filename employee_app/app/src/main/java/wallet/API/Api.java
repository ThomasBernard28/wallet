package wallet.API;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.io.File;

public class Api {

    private HttpClient           client   = HttpClient.newHttpClient();
    private HttpRequest          request;
    private HttpResponse<String> response;

    public String get_employee(String bic) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/bank/"+bic)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public String get_clients(String bic) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/clientVs/"+bic)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public String get_accountRequests(String bic) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/accRequest/"+bic)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    } 

    public String get_accounts(String bic) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/accounts/allByBic"+bic)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    } 

    public void put_addAccount(String requestID) throws Exception {
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/accRequest/validate/"+requestID))
            .header("Content-Type", "application/json")
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void put_language(String bic, String language) throws Exception {
    }
        
}
