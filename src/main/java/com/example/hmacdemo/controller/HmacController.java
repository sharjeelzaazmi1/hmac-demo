package com.example.hmacdemo.controller;

import com.example.hmacdemo.security.HMACUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HmacController {

    @GetMapping("/protected-resource")
    public String getProtectedResource() {
        return "This is a protected resource!";
    }

    private static final String SECRET_KEY = "YourSecretKey";  // The shared secret key for HMAC generation

    @GetMapping("/generate")
    public String createHMAC() {
        try {
            String requestData = "Data to be authenticated";

            // Generate HMAC for the request data
            String hmac =  HMACUtil.generateHMAC(requestData, SECRET_KEY);
            return hmac;  // Return the HMAC to the client
        } catch (Exception e) {
            // Handle exception if HMAC generation fails
            return "Error generating HMAC: " + e.getMessage();
        }
    }
}
