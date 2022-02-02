package com.project.agent.domain;

import javax.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int timesSold;

    @Column
    private double totalProfit;

    public Report() {
        this.timesSold = 0;
        this.totalProfit = 0;
    }

    public Report(int timesSold, double totalProfit) {
        this.timesSold = timesSold;
        this.totalProfit = totalProfit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(int timesSold) {
        this.timesSold = timesSold;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
