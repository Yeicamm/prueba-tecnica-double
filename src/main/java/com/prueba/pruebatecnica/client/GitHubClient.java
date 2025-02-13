package com.prueba.pruebatecnica.client;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/github")
public class GitHubClient {

    private final String GITHUB_API_URL = "https://api.github.com/search/users?q=YOUR_NAME";

    @GetMapping("/users")
    public String getUsers( ) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GITHUB_API_URL, String.class);
    }
}
