/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
/**
 *
 * @author peter
 */
public class ApiFetchCallable implements Callable<String> {
    private String host;
    private String method;

    public ApiFetchCallable(String host, String method) {
        this.host = host;
        this.method = method;
    }

    @Override
    public String call() throws Exception {
        String response = null;

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(host).openConnection();
       urlConnection.setRequestProperty("Accept", "application/json");
       urlConnection.setRequestMethod(method);

        
        BufferedReader br = new BufferedReader(new InputStreamReader (urlConnection.getInputStream()));
        String i;
        while ((i = br.readLine()) != null)
        {
            response = i;
        }
        return response;
    }
}
