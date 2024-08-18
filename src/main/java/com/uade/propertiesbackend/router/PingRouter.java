package com.uade.propertiesbackend.router;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingRouter {

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("pong");
    }
}
