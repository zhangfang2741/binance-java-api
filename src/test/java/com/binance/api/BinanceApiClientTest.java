package com.binance.api;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.market.ExchangeInfo;
import org.junit.Test;

import static com.binance.api.client.domain.account.NewOrder.marketBuy;

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
        NewOrder order = marketBuy("SHIBUSDT", "1");
        order.recvWindow(50000L);
        NewOrderResponse newOrderResponse = client.newOrder(order);
        System.out.println(newOrderResponse);
    }
    @Test
    public void testGetExchangeInfo() {
        String shibusdt = client.getExchangeInfo("SHIBUSDT");
        System.out.println(shibusdt);
    }
}
