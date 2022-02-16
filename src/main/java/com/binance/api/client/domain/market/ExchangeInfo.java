package com.binance.api.client.domain.market;

import java.util.List;

/**
 * 交易规范信息
 * doc:https://binance-docs.github.io/apidocs/spot/cn/#e7746f7d60
 */
public class ExchangeInfo {
    private String timezone;
    private Long serverTime;
    private List<ExchangeRateLimit> rateLimits;
    /**
     * 过滤器，即Filter，定义了一系列交易规则,共有两类，
     * 分别是针对交易对的过滤器symbol filters，
     * 和针对整个交易所的过滤器 exchange filters
     */
    private List<ExchangeFilter> exchangeFilters;
    private List<ExchangeSymbol> symbols;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = serverTime;
    }

    public List<ExchangeRateLimit> getRateLimits() {
        return rateLimits;
    }

    public void setRateLimits(List<ExchangeRateLimit> rateLimits) {
        this.rateLimits = rateLimits;
    }

    public List<ExchangeFilter> getExchangeFilters() {
        return exchangeFilters;
    }

    public void setExchangeFilters(List<ExchangeFilter> exchangeFilters) {
        this.exchangeFilters = exchangeFilters;
    }

    public List<ExchangeSymbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<ExchangeSymbol> symbols) {
        this.symbols = symbols;
    }
}
