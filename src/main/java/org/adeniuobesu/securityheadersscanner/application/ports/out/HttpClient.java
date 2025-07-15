package org.adeniuobesu.securityheadersscanner.application.ports.out;

import java.util.Map;

public interface HttpClient {
    Map<String, String> fetchHeaders(String url);
}
