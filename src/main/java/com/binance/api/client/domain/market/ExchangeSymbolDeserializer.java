package com.binance.api.client.domain.market;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Custom deserializer for an OrderBookEntry, since the API returns an array in the format [ price, qty, [] ].
 */
public class ExchangeSymbolDeserializer extends JsonDeserializer<ExchangeSymbol> {

    @Override
    public ExchangeSymbol deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        return new ExchangeSymbol();
    }
}
