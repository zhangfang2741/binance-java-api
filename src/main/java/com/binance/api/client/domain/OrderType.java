package com.binance.api.client.domain;

/**
 * Type of order to submit to the system.
 */
public enum OrderType {
    LIMIT,
    LIMIT_MAKER,
    MARKET,
    STOP_LOSS_LIMIT,
    TAKE_PROFIT_LIMIT

}
