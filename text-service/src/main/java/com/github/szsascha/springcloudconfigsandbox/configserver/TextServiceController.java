package com.github.szsascha.springcloudconfigsandbox.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TextServiceController {

    @Value("${textvalue:DEFAULTTEXT}")
    private String text;

    @GetMapping
    ResponseEntity<String> text() {
        return ResponseEntity.ok(text);
    }

}
