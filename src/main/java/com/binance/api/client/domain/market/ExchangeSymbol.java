package com.binance.api.client.domain.market;

import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.Permission;

import java.util.List;

public class ExchangeSymbol {
    private String symbol;
    private String status;
    private String baseAsset;
    private Integer baseAssetPrecision;
    private String quoteAsset;
    private Integer quotePrecision;
    private Integer quoteAssetPrecision;
    private Integer baseCommissionPrecision;
    private Integer quoteCommissionPrecision;
    private List<OrderType> orderTypes;
    private Boolean icebergAllowed;
    private Boolean ocoAllowed;
    private Boolean quoteOrderQtyMarketAllowed;
    private Boolean isSpotTradingAllowed;
    private Boolean isMarginTradingAllowed;
    /**
     * 交易对的过滤器symbol filters
     */
    private List<ExchangeSymbolFilter> filters;
    private List<Permission> permissions;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public Integer getBaseAssetPrecision() {
        return baseAssetPrecision;
    }

    public void setBaseAssetPrecision(Integer baseAssetPrecision) {
        this.baseAssetPrecision = baseAssetPrecision;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public Integer getQuotePrecision() {
        return quotePrecision;
    }

    public void setQuotePrecision(Integer quotePrecision) {
        this.quotePrecision = quotePrecision;
    }

    public Integer getQuoteAssetPrecision() {
        return quoteAssetPrecision;
    }

    public void setQuoteAssetPrecision(Integer quoteAssetPrecision) {
        this.quoteAssetPrecision = quoteAssetPrecision;
    }

    public Integer getBaseCommissionPrecision() {
        return baseCommissionPrecision;
    }

    public void setBaseCommissionPrecision(Integer baseCommissionPrecision) {
        this.baseCommissionPrecision = baseCommissionPrecision;
    }

    public Integer getQuoteCommissionPrecision() {
        return quoteCommissionPrecision;
    }

    public void setQuoteCommissionPrecision(Integer quoteCommissionPrecision) {
        this.quoteCommissionPrecision = quoteCommissionPrecision;
    }

    public List<OrderType> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(List<OrderType> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public Boolean getIcebergAllowed() {
        return icebergAllowed;
    }

    public void setIcebergAllowed(Boolean icebergAllowed) {
        this.icebergAllowed = icebergAllowed;
    }

    public Boolean getOcoAllowed() {
        return ocoAllowed;
    }

    public void setOcoAllowed(Boolean ocoAllowed) {
        this.ocoAllowed = ocoAllowed;
    }

    public Boolean getQuoteOrderQtyMarketAllowed() {
        return quoteOrderQtyMarketAllowed;
    }

    public void setQuoteOrderQtyMarketAllowed(Boolean quoteOrderQtyMarketAllowed) {
        this.quoteOrderQtyMarketAllowed = quoteOrderQtyMarketAllowed;
    }

    public Boolean getSpotTradingAllowed() {
        return isSpotTradingAllowed;
    }

    public void setSpotTradingAllowed(Boolean spotTradingAllowed) {
        isSpotTradingAllowed = spotTradingAllowed;
    }

    public Boolean getMarginTradingAllowed() {
        return isMarginTradingAllowed;
    }

    public void setMarginTradingAllowed(Boolean marginTradingAllowed) {
        isMarginTradingAllowed = marginTradingAllowed;
    }

    public List<ExchangeSymbolFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<ExchangeSymbolFilter> filters) {
        this.filters = filters;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
