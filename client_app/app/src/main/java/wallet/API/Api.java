package wallet.API;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Api {

    private HttpClient           client   = HttpClient.newHttpClient();
    private HttpRequest          request;
    private HttpResponse<String> response;

    public String get_user(String userID) throws IOException, InterruptedException {
        request  = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/user/"+userID)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public void post_user(String userJson) throws Exception {
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/user"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(userJson))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String get_wallets(String userID) throws IOException, InterruptedException {
        request  = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/wallets/"+userID)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public void post_wallet(String walletJson) throws Exception {
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/wallets"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(walletJson))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String get_products(int userID) throws IOException, InterruptedException {
        request  = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/accounts/"+Integer.toString(userID))).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public void post_transaction(String transactionJson) throws Exception {
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/transactions"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(transactionJson))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void post_deposit(String iban, String amount) throws Exception {
        String json = "{\"ibanReceiver\":\""+iban+"\",\"operType\":\"CASH_DEPOSIT\",\"currency\":\"EUR\",\"amount\":\""+amount+"\",\"dateTime\":\""+LocalDateTime.now().minusMinutes(122).toString()+"\",\"weekend\":\"0\",\"status\":\"0\",\"comments\":\"cash deposit\"}";
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/transactions/cashDeposit"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
        

    public void put_password(String userID, String psswd) throws IOException, InterruptedException {
        String json = "{\"userID\":\""+userID+"\", \"psswd\":\""+psswd+"\"}";
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/user/psswd/"+userID))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void put_language(String userID, String language) throws IOException, InterruptedException {
        String json = "{\"userID\":\""+userID+"\", \"language\":\""+language+"\"}";
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/user/language/"+userID))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void put_walletActivity(int walletID, int activity) throws IOException, InterruptedException {
        String json = "{\"activity\":\""+Integer.toString(activity)+"\"}";
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/wallets/"+Integer.toString(walletID)))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void put_accountActivity(String iban, int activity) throws IOException, InterruptedException {
        String json = "{\"activity\":\""+Integer.toString(activity)+"\"}";
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://sierra880.xyz:4545/api/v1/accounts/"+iban))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String get_banks() throws IOException, InterruptedException {
        request  = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/bank")).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }

    public String get_apiTime() throws IOException, InterruptedException {
        request  = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(URI.create("http://sierra880.xyz:4545/api/v1/transactions/localDate")).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();     // return the json
    }
        

}
