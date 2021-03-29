package com.service;

import com.model.response.StatisticsResponse;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private StatisticsResponse statisticsResponse = new StatisticsResponse();

    public void updateStatistics(double newDistance) {
        double closesDistance;
        double farthesDistance;
        double totalDistance;

        closesDistance = statisticsResponse.getClosestDistance();
        farthesDistance = statisticsResponse.getFarthestDistance();
        totalDistance = statisticsResponse.getTotalDistance();

        if (newDistance < closesDistance || closesDistance == 0) {
            statisticsResponse.setClosestDistance(newDistance);
        }
        if (newDistance > farthesDistance || farthesDistance == 0) {
            statisticsResponse.setFarthestDistance(newDistance);
        }
        statisticsResponse.setTotal(statisticsResponse.getTotal() + 1);
        totalDistance += newDistance;
        statisticsResponse.setTotalDistance(totalDistance);
        statisticsResponse.setAverageDistance(totalDistance/ statisticsResponse.getTotal());

    }

    public StatisticsResponse getStatistics() {
        return statisticsResponse;

    }
}
