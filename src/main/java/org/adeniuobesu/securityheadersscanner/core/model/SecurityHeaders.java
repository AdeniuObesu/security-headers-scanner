package org.adeniuobesu.securityheadersscanner.core.model;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public class SecurityHeaders {
    private final Map<String, String> rawHeaders;

    public SecurityHeaders(Map<String, String> headers) {
        this.rawHeaders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.rawHeaders.putAll(headers);
    }

    public Optional<String> get(String headerName) {
        return Optional.ofNullable(rawHeaders.get(headerName));
    }

    public boolean contains(String headerName) {
        return rawHeaders.containsKey(headerName);
    }

    public Set<String> names() {
        return rawHeaders.keySet();
    }

    public Map<String, String> asMap() {
        return Collections.unmodifiableMap(rawHeaders);
    }
}
