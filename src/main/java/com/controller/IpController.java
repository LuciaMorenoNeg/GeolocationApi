package com.controller;

import com.service.IpService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class IpController {
    private final IpService ipService;

    @Autowired
    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @GetMapping
    public ResponseEntity<String> getIpData(@RequestBody JSONObject ipJson) throws Exception {
        String ip = ipJson.getString("ip");
        ipService.getInformation(ip);
        return ResponseEntity.ok("Hola!");
    }
}
