package com.binance.api;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.market.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BinanceApiClientTest {
    String apiKey = "rHPNZsYeAY6CrgR55TgH9bQTgQv5PzXdwpGRbT9l8fqUoxwqOEdrBR735GZVhYrX";
    String secret = "xliKhzqSkN3xiWliDuCYdYmUnfobYHWT9YwYrSaNC8mXSCSw5v3AiEdTYDglNd5a";
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(apiKey, secret);
    BinanceApiRestClient client = factory.newRestClient();

    @Test
    public void init() {
        client.ping();
    }

    @Test
    public void testAssetsInfo() {
    }

    @Test
    public void testTrade() {
        NewOrder order = new NewOrder("SHIBUSDT", OrderSide.BUY, OrderType.LIMIT, TimeInForce.GTC,"350000","0.00002898");
        order.recvWindow(50000L);
        if (checkOrder(order)) {
            NewOrderResponse newOrderResponse = client.newOrder(order);
            System.out.println(newOrderResponse);
        }
    }

    @Test
    public void testGetExchangeInfo() {
        ExchangeInfo shibusdt = client.getExchangeInfo("SHIBUSDT");
        System.out.println(shibusdt);
    }

    public Boolean checkOrder(NewOrder order) {
        String symbol = order.getSymbol();
        ExchangeInfo exchangeInfo = client.getExchangeInfo(symbol);
        Optional<ExchangeSymbol> optional = exchangeInfo.getSymbols().stream().filter(s -> s.getSymbol().equals(symbol)).findFirst();
        ExchangeSymbol exchangeSymbol = optional.get();
        Map<ExchangeSymbolFilterType, ExchangeSymbolFilter> filterMap = new HashMap<>();
        exchangeSymbol.getFilters().forEach(filter -> {
            ExchangeSymbolFilterAnnotation annotation = filter.getClass().getDeclaredAnnotation(ExchangeSymbolFilterAnnotation.class);
            filterMap.put(annotation.type(), filter);
        });
        //标的数量检查（订单尺寸：LOT_SIZE）
        Double orderQuantity = Double.valueOf(order.getQuantity());
        ExchangeSymbolLotSizeFilter symbolLotSizeFilter = (ExchangeSymbolLotSizeFilter) filterMap.get(ExchangeSymbolFilterType.LOT_SIZE);
        if (orderQuantity < symbolLotSizeFilter.getMinQty() || orderQuantity > symbolLotSizeFilter.getMaxQty()) {
            System.out.println("订单标的数量不合法,[" + symbol + "]范围为[" + new BigDecimal(symbolLotSizeFilter.getMinQty()).toPlainString() + "," + new BigDecimal(symbolLotSizeFilter.getMaxQty()).toPlainString() + "]");
            return Boolean.FALSE;
        }
        if ((orderQuantity - symbolLotSizeFilter.getMinQty()) % symbolLotSizeFilter.getStepSize() != 0) {
            System.out.println("订单标的数量的步进间隔不合法,[" + symbol + "]步进间隔为[" + new BigDecimal(symbolLotSizeFilter.getMinQty()).toPlainString() + "+" + new BigDecimal(symbolLotSizeFilter.getStepSize()).toPlainString() + "*n]");
            return Boolean.FALSE;
        }
        //标的价格检查(PRICE_FILTER)
        Double orderPrice = Double.valueOf(order.getPrice());
        ExchangeSymbolPriceFilter symbolPriceFilter = (ExchangeSymbolPriceFilter) filterMap.get(ExchangeSymbolFilterType.PRICE_FILTER);
        if (orderPrice < symbolPriceFilter.getMinPrice() || orderPrice > symbolPriceFilter.getMaxPrice()) {
            System.out.println("订单标的数量不合法,[" + symbol + "]范围为[" + new BigDecimal(symbolPriceFilter.getMinPrice()).toPlainString() + "," + new BigDecimal(symbolPriceFilter.getMaxPrice()).toPlainString() + "]");
            return Boolean.FALSE;
        }
        //成交额检查(最小名义价值MIN_NOTIONAL)
        ExchangeSymbolMinNotionalFilter symbolMinNotionalFilter = (ExchangeSymbolMinNotionalFilter) filterMap.get(ExchangeSymbolFilterType.MIN_NOTIONAL);
        if (orderQuantity * orderPrice < symbolMinNotionalFilter.getMinNotional()) {
            System.out.println("订单成交额不合法,[" + symbol + "]最小成交额为[" + new BigDecimal(symbolMinNotionalFilter.getMinNotional()).toPlainString() + "]");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
