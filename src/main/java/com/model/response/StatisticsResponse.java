package com.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StatisticsResponse {
    private double farthestDistance;
    private double closestDistance;
    private double averageDistance;
    @JsonIgnore
    private double totalDistance;
    @JsonIgnore
    private int total;

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getFarthestDistance() {
        return farthestDistance;
    }

    public void setFarthestDistance(double farthestDistance) {
        this.farthestDistance = farthestDistance;
    }

    public double getClosestDistance() {
        return closestDistance;
    }

    public void setClosestDistance(double closestDistance) {
        this.closestDistance = closestDistance;
    }

    public double getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(double averageDistance) {
        this.averageDistance = averageDistance;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
