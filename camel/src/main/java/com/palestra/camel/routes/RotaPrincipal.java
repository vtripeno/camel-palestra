package com.palestra.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class RotaPrincipal extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:distribuidor")
            .multicast(new AggregationStrategy() {
                @Override
                public Exchange aggregate(Exchange exchange, Exchange exchange1) {
                	if(exchange1 != null) {
                		
                	}
                    return exchange;
                }
            }, true)
            .to("","")
            .end();
    }
}
