package com.palestra.camel.aggregation;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.json.JSONObject;

public class AgregacaoValor  implements AggregationStrategy {
    private String chaveJson;

    public AgregacaoValor(String chaveJson) {
        this.chaveJson = chaveJson;
    }

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        JSONObject jsonObject = oldExchange.getIn().getBody(JSONObject.class);
        jsonObject.put(chaveJson, newExchange.getIn().getBody(String.class));
        oldExchange.getIn().setBody(jsonObject);
        return oldExchange;
    }
}
