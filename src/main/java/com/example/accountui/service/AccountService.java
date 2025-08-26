package com.example.accountui.service;

import com.example.accountui.config.AccountApiProperties;
import com.example.accountui.model.Account;
import com.example.accountui.model.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
public class AccountService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private AccountApiProperties apiProperties;
    
    public List<Account> getAllAccounts() {
        String url = apiProperties.getBaseUrl() + "/accounts?limit=1000";
        HttpHeaders headers = createAuthHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<AccountResponse> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, AccountResponse.class);
        
        return response.getBody() != null ? response.getBody().getData() : List.of();
    }
    
    public Account getAccountById(String id) {
        String url = apiProperties.getBaseUrl() + "/accounts/" + id;
        HttpHeaders headers = createAuthHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<Account> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, Account.class);
        
        return response.getBody();
    }
    
    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = apiProperties.getUsername() + ":" + apiProperties.getPassword();
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}