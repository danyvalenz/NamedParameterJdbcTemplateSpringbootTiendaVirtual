package com.coppel.wsobtenerdatosclientetiendavirtual.filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coppel.wsobtenerdatosclientetiendavirtual.config.AppConfig;
import com.coppel.wsobtenerdatosclientetiendavirtual.dto.ApiResponseDTO;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.AppMessages;
import com.coppel.wsobtenerdatosclientetiendavirtual.util.Meta;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * SessionFilter
 */
@Component
@Order(value = 1)
public class SessionFilter implements Filter {

    @Autowired
    private AppConfig config;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
                if (config.isIgnoreSession()) {
                    chain.doFilter(request, response);
                    return;
                }
                final HttpServletRequest req = (HttpServletRequest) request;
                final HttpServletResponse res = (HttpServletResponse) response;
                final ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
                final ObjectMapper objectMapper = new ObjectMapper();
                if (req.getHeader(HttpHeaders.AUTHORIZATION) == null) {
                    res.reset();
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, config.getAllowedOrigins());
                    res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, config.getAllowedMethods());
                    res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, config.getAllowedHeaders());
                    res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    res.setCharacterEncoding("UTF-8");
                    apiResponseDTO.setMeta(new Meta(null, AppMessages.CLIENT_ERROR, 401, AppMessages.UNAUTHORISED_MESSAGE));
                    res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
                } else {
                    final RestTemplate client = new RestTemplate();
                    final HttpHeaders authHeaders = new HttpHeaders();
                    authHeaders.add(HttpHeaders.AUTHORIZATION, req.getHeader(HttpHeaders.AUTHORIZATION));
                    final HttpEntity<String> httpEntity = new HttpEntity<>("body", authHeaders);
                    try {
                        final ResponseEntity<String> authResponse = client.exchange(config.getAuthUri(), HttpMethod.POST, httpEntity, String.class);
                        if (authResponse.getStatusCode() == HttpStatus.OK) {
                            chain.doFilter(request, response);
                        }
                    } catch (RestClientException ex) {
                        res.reset();
                        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, config.getAllowedOrigins());
                        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, config.getAllowedMethods());
                        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, config.getAllowedHeaders());
                        res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        res.setCharacterEncoding("UTF-8");
                        apiResponseDTO.setMeta(new Meta(null, AppMessages.CLIENT_ERROR, 401, AppMessages.UNAUTHORISED_MESSAGE));
                        res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
                    }
                }
        }
}
