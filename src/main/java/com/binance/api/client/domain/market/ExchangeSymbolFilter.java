package com.binance.api.client.domain.market;

import com.binance.api.client.domain.ExchangeSymbolFilterType;

/**
 * 交易对的过滤器symbol filters
 */
public abstract class ExchangeSymbolFilter {
    private ExchangeSymbolFilterType filterType;

    public ExchangeSymbolFilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(ExchangeSymbolFilterType filterType) {
        this.filterType = filterType;
    }
}
