package com.binance.api.client.domain.market;

import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;

/**
 * PERCENT_PRICE 价格振幅过滤器
 * PERCENT_PRICE过滤器基于先前交易的平均值来定义价格的有效范围。
 * 为了通过"价格百分比"，"价格"必须符合以下条件：
 * price <=weightedAveragePrice(加权平均价格) *multiplierUp（乘数上）
 * price> =weightedAveragePrice(加权平均价格) *multiplierDown（乘数下）
 */
@ExchangeSymbolFilterAnnotation(type = ExchangeSymbolFilterType.PERCENT_PRICE)
public class ExchangeSymbolPercentPriceFilter extends ExchangeSymbolFilter {
    private Double multiplierUp;
    private Double multiplierDown;
    //avgPriceMins是计算平均价格的分钟数。 0表示使用最后的价格。
    private Integer avgPriceMins;

    public Double getMultiplierUp() {
        return multiplierUp;
    }

    public void setMultiplierUp(Double multiplierUp) {
        this.multiplierUp = multiplierUp;
    }

    public Double getMultiplierDown() {
        return multiplierDown;
    }

    public void setMultiplierDown(Double multiplierDown) {
        this.multiplierDown = multiplierDown;
    }

    public Integer getAvgPriceMins() {
        return avgPriceMins;
    }

    public void setAvgPriceMins(Integer avgPriceMins) {
        this.avgPriceMins = avgPriceMins;
    }

}
