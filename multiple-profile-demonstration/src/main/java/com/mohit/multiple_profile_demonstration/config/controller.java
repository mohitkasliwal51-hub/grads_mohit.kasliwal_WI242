package com.mohit.multiple_profile_demonstration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
@Value("${app.message}")
private String message;

    @GetMapping("/profile")
    public String getMessage() {
        return message;
    }
}
