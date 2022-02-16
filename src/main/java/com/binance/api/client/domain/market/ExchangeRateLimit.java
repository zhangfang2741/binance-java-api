package com.binance.api.client.domain.market;

public class ExchangeRateLimit {
    private String rateLimitType;
    private String interval;
    private Integer intervalNum;
    private Long limit;

    public String getRateLimitType() {
        return rateLimitType;
    }

    public void setRateLimitType(String rateLimitType) {
        this.rateLimitType = rateLimitType;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Integer getIntervalNum() {
        return intervalNum;
    }

    public void setIntervalNum(Integer intervalNum) {
        this.intervalNum = intervalNum;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
