package com.controller;

import com.model.response.IpInformation;
import com.service.IpService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class IpController {
    private final IpService ipService;

    @Autowired
    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @PostMapping("/trace")
    public ResponseEntity<IpInformation> getIpData(@RequestBody JSONObject ipJson) throws Exception {
        String ip = ipJson.getString("ip");
        IpInformation ipInformation = ipService.getInformation(ip);
        return ResponseEntity.ok(ipInformation);
    }
}
