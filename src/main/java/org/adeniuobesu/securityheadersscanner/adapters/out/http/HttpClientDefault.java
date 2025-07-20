package org.adeniuobesu.securityheadersscanner.adapters.out.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.adeniuobesu.securityheadersscanner.application.ports.out.HttpClient;
import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;

public class HttpClientDefault implements HttpClient {

    @Override
    public SecurityHeaders fetchHeaders(String url) {
        Map<String, String> rawHeaders = new HashMap<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.connect();

            int i = 1;
            String headerKey = connection.getHeaderFieldKey(0);
            String headerValue = connection.getHeaderField(0);
            while ((headerKey = connection.getHeaderFieldKey(i)) != null) {
                headerValue = connection.getHeaderField(i);
                if (headerKey != null && headerValue != null) {
                    rawHeaders.put(headerKey, headerValue);
                }
                i++;
            }
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("❌ Failed to fetch headers: " + e.getMessage());
        }

        return new SecurityHeaders(rawHeaders);
    }
    
}
