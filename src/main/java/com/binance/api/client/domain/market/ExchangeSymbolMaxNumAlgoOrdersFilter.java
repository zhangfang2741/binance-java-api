package com.binance.api.client.domain.market;


public class ExchangeSymbolMaxNumAlgoOrdersFilter extends ExchangeSymbolFilter {
    private Integer maxNumAlgoOrders;

    public Integer getMaxNumAlgoOrders() {
        return maxNumAlgoOrders;
    }

    public void setMaxNumAlgoOrders(Integer maxNumAlgoOrders) {
        this.maxNumAlgoOrders = maxNumAlgoOrders;
    }
}
