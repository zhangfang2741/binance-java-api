package com.binance.api.client.domain.market;

import com.alibaba.fastjson.JSON;
import com.binance.api.client.annontation.ExchangeSymbolFilterAnnotation;
import com.binance.api.client.domain.ExchangeSymbolFilterType;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.Permission;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;

/**
 * Custom deserializer for an OrderBookEntry, since the API returns an array in the format [ price, qty, [] ].
 */
public class ExchangeInfoDeserializer extends JsonDeserializer<ExchangeInfo> {
    private static Map<ExchangeSymbolFilterType, Class<? extends ExchangeSymbolFilter>> exchangeSymbolFilterMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections(ExchangeSymbolFilter.class.getPackage().getName());
        Set<Class<? extends ExchangeSymbolFilter>> implClasses = reflections.getSubTypesOf(ExchangeSymbolFilter.class);
        for (Class<? extends ExchangeSymbolFilter> implClass : implClasses) {
            ExchangeSymbolFilterAnnotation annotation = implClass.getDeclaredAnnotation(ExchangeSymbolFilterAnnotation.class);
            exchangeSymbolFilterMap.put(annotation.type(), implClass);
        }
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                return ExchangeSymbolFilterType.valueOf((String) value);
            }
        }, ExchangeSymbolFilterType.class);
    }

    @Override
    public ExchangeInfo deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        ExchangeInfo exchangeInfo = new ExchangeInfo();
        exchangeInfo.setTimezone(node.get("timezone").asText());
        exchangeInfo.setServerTime(node.get("serverTime").asLong());
        List<ExchangeRateLimit> rateLimits = new ArrayList<>();
        Iterator<JsonNode> rateLimitNodes = node.get("rateLimits").iterator();
        while (rateLimitNodes.hasNext()) {
            ExchangeRateLimit exchangeRateLimit = new ExchangeRateLimit();
            JsonNode rateLimitNode = rateLimitNodes.next();
            exchangeRateLimit.setRateLimitType(rateLimitNode.get("rateLimitType").asText());
            exchangeRateLimit.setInterval(rateLimitNode.get("interval").asText());
            exchangeRateLimit.setIntervalNum(rateLimitNode.get("intervalNum").asInt());
            exchangeRateLimit.setLimit(rateLimitNode.get("limit").asLong());
            rateLimits.add(exchangeRateLimit);
        }
        exchangeInfo.setRateLimits(rateLimits);

        List<ExchangeFilter> exchangeFilters = new ArrayList<>();
        Iterator<JsonNode> exchangeFilterNodes = node.get("exchangeFilters").iterator();
        while (exchangeFilterNodes.hasNext()) {
            ExchangeFilter exchangeFilter = new ExchangeFilter();
            exchangeFilters.add(exchangeFilter);
        }
        exchangeInfo.setExchangeFilters(exchangeFilters);
        List<ExchangeSymbol> exchangeSymbols = new ArrayList<>();
        Iterator<JsonNode> exchangeSymbolNodes = node.get("symbols").iterator();
        while (exchangeSymbolNodes.hasNext()) {
            ExchangeSymbol exchangeSymbol = new ExchangeSymbol();
            JsonNode exchangeSymbolNode = exchangeSymbolNodes.next();
            exchangeSymbol.setSymbol(exchangeSymbolNode.get("symbol").asText());
            exchangeSymbol.setStatus(exchangeSymbolNode.get("status").asText());

            exchangeSymbol.setBaseAsset(exchangeSymbolNode.get("baseAsset").asText());
            exchangeSymbol.setBaseAssetPrecision(exchangeSymbolNode.get("baseAssetPrecision").asInt());
            exchangeSymbol.setQuoteAsset(exchangeSymbolNode.get("quoteAsset").asText());
            exchangeSymbol.setQuotePrecision(exchangeSymbolNode.get("quotePrecision").asInt());
            exchangeSymbol.setQuoteAssetPrecision(exchangeSymbolNode.get("quoteAssetPrecision").asInt());

            exchangeSymbol.setBaseCommissionPrecision(exchangeSymbolNode.get("baseCommissionPrecision").asInt());
            exchangeSymbol.setQuoteCommissionPrecision(exchangeSymbolNode.get("quoteCommissionPrecision").asInt());
            List<OrderType> orderTypes = new ArrayList<>();
            Iterator<JsonNode> orderTypeNodes = exchangeSymbolNode.get("orderTypes").iterator();
            while (orderTypeNodes.hasNext()) {
                orderTypes.add(OrderType.valueOf(orderTypeNodes.next().asText()));
            }
            exchangeSymbol.setOrderTypes(orderTypes);
            exchangeSymbol.setIcebergAllowed(exchangeSymbolNode.get("icebergAllowed").asBoolean());
            exchangeSymbol.setOcoAllowed(exchangeSymbolNode.get("ocoAllowed").asBoolean());
            exchangeSymbol.setQuoteOrderQtyMarketAllowed(exchangeSymbolNode.get("quoteOrderQtyMarketAllowed").asBoolean());
            exchangeSymbol.setSpotTradingAllowed(exchangeSymbolNode.get("isSpotTradingAllowed").asBoolean());
            exchangeSymbol.setMarginTradingAllowed(exchangeSymbolNode.get("isMarginTradingAllowed").asBoolean());
            List<ExchangeSymbolFilter> exchangeSymbolFilters = new ArrayList<>();
            Iterator<JsonNode> exchangeSymbolFilterNodes = exchangeSymbolNode.get("filters").iterator();
            while (exchangeSymbolFilterNodes.hasNext()) {
                JsonNode exchangeFilterNode = exchangeSymbolFilterNodes.next();
                try {
                    ExchangeSymbolFilterType exchangeSymbolFilterType = ExchangeSymbolFilterType.valueOf(exchangeFilterNode.get("filterType").asText());
                    Map<String, Object> config = JSON.parseObject(exchangeFilterNode.toString(), Map.class);
                    ExchangeSymbolFilter exchangeSymbolFilter = exchangeSymbolFilterMap.get(exchangeSymbolFilterType).newInstance();
                    BeanUtils.populate(exchangeSymbolFilter, config);
                    exchangeSymbolFilters.add(exchangeSymbolFilter);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            exchangeSymbol.setFilters(exchangeSymbolFilters);
            List<Permission> permissions = new ArrayList<>();
            Iterator<JsonNode> permissionNodes = exchangeSymbolNode.get("permissions").iterator();
            while (permissionNodes.hasNext()) {
                permissions.add(Permission.valueOf(permissionNodes.next().asText()));
            }
            exchangeSymbol.setPermissions(permissions);
            exchangeSymbols.add(exchangeSymbol);
        }
        exchangeInfo.setSymbols(exchangeSymbols);
        return exchangeInfo;
    }
}
