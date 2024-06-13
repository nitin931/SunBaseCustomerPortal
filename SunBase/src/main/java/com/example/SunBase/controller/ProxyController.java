package com.example.SunBase.controller;

import com.example.SunBase.models.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {

    @Autowired
    private RestTemplate restTemplate;

    private final String externalApiBaseUrl = "https://qa.sunbasedata.com/sunbase/portal/api";
    String token = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";
    // Endpoint to authenticate and retrieve token
    @PostMapping("/get-token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        String apiUrl = externalApiBaseUrl + "/assignment_auth.jsp";
        ResponseEntity<String> responseEntity =  restTemplate.postForEntity(apiUrl, authRequest, String.class);
        token = extractAccessToken(responseEntity.getBody());

        return responseEntity;
    }

    private String extractAccessToken(String responseBody) {
        try {
            // Assuming responseBody is {"access_token":"salfjnlds"}
            // Parse JSON to get access token value
            return responseBody.split(":")[1].replaceAll("[\"}]", "").trim();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/get-customer-list")
    public ResponseEntity<String> getCustomerList(HttpServletRequest request) {
        //String token = (String) request.getSession().getAttribute("token");
        System.out.println(token);
        // Remove any potential new line characters or invalid symbols from the token

        System.out.println(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Trim to remove any leading/trailing whitespace

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrl = externalApiBaseUrl + "/assignment.jsp?cmd=get_customer_list";

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        return ResponseEntity.ok().body(responseEntity.getBody());
    }
}
