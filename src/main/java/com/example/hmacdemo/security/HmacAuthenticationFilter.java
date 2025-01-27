package com.example.hmacdemo.security;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class HmacAuthenticationFilter implements Filter {

    private static final String SECRET_KEY = "YourSecretKey"; // Shared secret key for HMAC validation
    private static final String HMAC_HEADER = "X-HMAC"; // Header that contains the HMAC

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String requestData = "Data to be authenticated"; // In a real-world case, use the actual request data.
        String receivedHmac = ((HttpServletRequest) request).getHeader(HMAC_HEADER);

        if (receivedHmac == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing HMAC header");
            return;
        }

        try {
            // Calculate the expected HMAC based on the request data and secret key
            String expectedHmac = HMACUtil.calculateHMAC(requestData, SECRET_KEY);

            // Compare the received HMAC with the expected one
            if (!receivedHmac.equals(expectedHmac)) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid HMAC");
                return;
            }

            // If HMAC is valid, continue with the request
            chain.doFilter(request, response);

        } catch (Exception e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "HMAC Calculation Error");
        }
    }

    @Override
    public void destroy() {}
}
