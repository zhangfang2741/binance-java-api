package com.binance.api.client.annontation;

import com.binance.api.client.domain.ExchangeSymbolFilterType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExchangeSymbolFilterAnnotation {
    ExchangeSymbolFilterType type();
}
