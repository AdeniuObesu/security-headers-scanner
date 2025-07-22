package org.adeniuobesu.securityheadersscanner.application.ports.out;

import org.adeniuobesu.securityheadersscanner.core.model.SecurityHeaders;

public interface HttpClient {
    SecurityHeaders fetchHeaders(String url);
}
