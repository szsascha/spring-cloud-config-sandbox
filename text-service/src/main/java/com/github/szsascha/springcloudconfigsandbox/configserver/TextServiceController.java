package com.github.szsascha.springcloudconfigsandbox.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class TextServiceController {

    @Value("${textvalue:DEFAULTTEXT}")
    private String text;

    @GetMapping
    ResponseEntity<String> text() {
        return ResponseEntity.ok(text);
    }

}
