package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class IpController {

    @GetMapping
    public ResponseEntity<String> getIpData() {
        return ResponseEntity.ok("Hola!");
    }
}
