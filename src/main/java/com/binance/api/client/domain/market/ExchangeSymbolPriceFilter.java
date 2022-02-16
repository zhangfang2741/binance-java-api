package com.binance.api.client.domain.market;

/**
 * PRICE_FILTER 价格过滤器
 * 价格过滤器 用于检测订单中 price 参数的合法性
 * 逻辑伪代码如下:
 * price >= minPrice
 * price <= maxPrice
 * (price-minPrice) % tickSize == 0
 */
public class ExchangeSymbolPriceFilter extends ExchangeSymbolFilter {
    //minPrice 定义了 price/stopPrice 允许的最小值
    private Double minPrice;
    //maxPrice 定义了 price/stopPrice 允许的最大值。
    private Double maxPrice;
    //tickSize 定义了 price/stopPrice 的步进间隔，即price必须等于minPrice+(tickSize的整数倍)
    private Double tickSize;
    //以上每一项均可为0，为0时代表这一项不再做限制。

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getTickSize() {
        return tickSize;
    }

    public void setTickSize(Double tickSize) {
        this.tickSize = tickSize;
    }
}
