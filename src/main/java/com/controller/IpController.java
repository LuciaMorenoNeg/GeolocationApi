package com.controller;

import com.exception.ApiException;
import com.model.response.IpInformation;
import com.model.response.StatisticsResponse;
import com.service.IpService;
import com.service.StatsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class IpController {
    private final IpService ipService;
    private final StatsService statsService;

    @Autowired
    public IpController(IpService ipService, StatsService statsService) {
        this.ipService = ipService;
        this.statsService = statsService;
    }

    @PostMapping("/trace/")
    public ResponseEntity getIpData(@RequestBody JSONObject ipJson)  {
        try {
            String ip = ipJson.getString("ip");
            IpInformation ipInformation = ipService.getInformation(ip);
            return ResponseEntity.ok(ipInformation);
        }
        catch (ApiException e){
            JSONObject json = new JSONObject();
            json.put("message", e.getDescription());
            return ResponseEntity.status(e.getStatus()).body(json);
        }


    }

    @GetMapping("/stats/")
    public ResponseEntity getStatistics() {
        try {
            StatisticsResponse statisticsResponse = statsService.getStatistics();
            return ResponseEntity.ok(statisticsResponse);
        }
        catch (ApiException e){
            JSONObject json = new JSONObject();
            json.put("message", e.getDescription());
            return ResponseEntity.status(e.getStatus()).body(json);
        }

    }
}
