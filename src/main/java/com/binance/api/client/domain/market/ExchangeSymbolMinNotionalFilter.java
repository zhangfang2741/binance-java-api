package com.binance.api.client.domain.market;

/**
 * MIN_NOTIONAL 最小名义价值(成交额)
 * MIN_NOTIONAL过滤器定义了交易对订单所允许的最小名义价值(成交额)。 订单的名义价值是价格*数量。 如果是高级订单(比如止盈止损订单STOP_LOSS_LIMIT)，名义价值会按照stopPrice * quantity来计算。 如果是冰山订单，名义价值会按照price * icebergQty来计算。
 * 由于MARKET订单没有价格，因此会在最后avgPriceMins分钟内使用平均价格。
 *
 */
public class ExchangeSymbolMinNotionalFilter extends ExchangeSymbolFilter {
    //最小名义价值
    private Double minNotional;
    //applyToMarket确定 MIN_NOTIONAL过滤器是否也将应用于MARKET订单。
    private Boolean applyToMarket;
    //avgPriceMins是计算平均价格的分钟数。 0表示使用最后的价格。
    private Integer avgPriceMins;

    public Double getMinNotional() {
        return minNotional;
    }

    public void setMinNotional(Double minNotional) {
        this.minNotional = minNotional;
    }

    public Boolean getApplyToMarket() {
        return applyToMarket;
    }

    public void setApplyToMarket(Boolean applyToMarket) {
        this.applyToMarket = applyToMarket;
    }

    public Integer getAvgPriceMins() {
        return avgPriceMins;
    }

    public void setAvgPriceMins(Integer avgPriceMins) {
        this.avgPriceMins = avgPriceMins;
    }
}
