package com.binance.api.client.domain.market;

import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;

import java.util.Map;

/**
 * LOT_SIZE 订单尺寸
 * Lots是拍卖术语，LOT_SIZE 过滤器对订单中的 quantity 也就是数量参数进行合法性检查
 * 逻辑伪代码如下:
 * <p>
 * quantity >= minQty
 * quantity <= maxQty
 * (quantity-minQty) % stepSize == 0
 */
@ExchangeSymbolFilterAnnotation(type = ExchangeSymbolFilterType.LOT_SIZE)
public class ExchangeSymbolLotSizeFilter extends ExchangeSymbolFilter {
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
