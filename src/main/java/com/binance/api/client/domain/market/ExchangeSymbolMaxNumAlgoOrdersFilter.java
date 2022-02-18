package com.binance.api.client.domain.market;

import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;

@ExchangeSymbolFilterAnnotation(type = ExchangeSymbolFilterType.MAX_NUM_ALGO_ORDERS)
public class ExchangeSymbolMaxNumAlgoOrdersFilter extends ExchangeSymbolFilter {
    private Integer maxNumAlgoOrders;

    public Integer getMaxNumAlgoOrders() {
        return maxNumAlgoOrders;
    }

    public void setMaxNumAlgoOrders(Integer maxNumAlgoOrders) {
        this.maxNumAlgoOrders = maxNumAlgoOrders;
    }

}
