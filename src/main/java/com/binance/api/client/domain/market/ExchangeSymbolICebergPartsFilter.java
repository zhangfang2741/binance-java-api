package com.binance.api.client.domain.market;

/**
 * ICEBERG_PARTS 冰山订单拆分数
 * ICEBERG_PARTS 代表冰山订单最多可以拆分成多少个小订单。
 * 计算方法为 向上取整(qty / icebergQty)。
 */
public class ExchangeSymbolICebergPartsFilter extends ExchangeSymbolFilter {
    //订单最多可以拆分成多少个小订单。
    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
