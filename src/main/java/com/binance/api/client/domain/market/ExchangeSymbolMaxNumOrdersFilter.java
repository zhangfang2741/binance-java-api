package com.binance.api.client.domain.market;

import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;

/**
 * MAX_NUM_ORDERS 最多订单数
 * 定义了某个交易对最多允许的挂单数量(不包括已关闭的订单)
 * 普通订单与条件订单均计算在内
 */
@ExchangeSymbolFilterAnnotation(type = ExchangeSymbolFilterType.MAX_NUM_ORDERS)
public class ExchangeSymbolMaxNumOrdersFilter extends ExchangeSymbolFilter {
    private Integer maxNumOrders;

    public Integer getMaxNumOrders() {
        return maxNumOrders;
    }

    public void setMaxNumOrders(Integer maxNumOrders) {
        this.maxNumOrders = maxNumOrders;
    }

}
