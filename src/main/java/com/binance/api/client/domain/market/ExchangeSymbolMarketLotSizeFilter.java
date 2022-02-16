package com.binance.api.client.domain.market;

/**
 * MARKET_LOT_SIZE 市价订单尺寸
 * MARKET_LOT_SIZE过滤器为交易对上的MARKET订单定义了数量(即拍卖中的"手数")规则。 共有3部分：
 * minQty定义了允许的最小quantity。
 * maxQty定义了允许的最大数量。
 * stepSize定义了可以增加/减少数量的间隔。
 * 为了通过market lot size，quantity必须满足以下条件：
 *
 * quantity >= minQty
 * quantity <= maxQty
 * (quantity-minQty) % stepSize == 0
 */
public class ExchangeSymbolMarketLotSizeFilter extends ExchangeSymbolFilter {
    //minQty 表示 quantity/icebergQty 允许的最小值。
    private Double minQty;
    //maxQty 表示 quantity/icebergQty 允许的最大值。
    private Double maxQty;
    //stepSize 表示 quantity/icebergQty 允许的步进值。
    private Double stepSize;

    public Double getMinQty() {
        return minQty;
    }

    public void setMinQty(Double minQty) {
        this.minQty = minQty;
    }

    public Double getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Double maxQty) {
        this.maxQty = maxQty;
    }

    public Double getStepSize() {
        return stepSize;
    }

    public void setStepSize(Double stepSize) {
        this.stepSize = stepSize;
    }
}
