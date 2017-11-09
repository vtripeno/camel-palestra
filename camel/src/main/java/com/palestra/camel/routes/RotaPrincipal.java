package com.palestra.camel.routes;

import com.palestra.camel.aggregation.AgregacaoValor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class RotaPrincipal extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	/*errorHandler(
				deadLetterChannel("direct:error")
				.logExhausted(true)
		);
    	
    	from("direct:error").autoStartup(true).id("error")
            .setHeader("CamelAcceptContentType", constant("application/json"))
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
    	.end();*/


        from("direct:distribuidor").autoStartup(true).id("distribuidor")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .process(exchange -> {
                JSONObject jsonObject = new JSONObject(exchange.getIn().getBody(String.class));
                exchange.setProperty("score", jsonObject.get("score"));
                exchange.getIn().setBody(jsonObject);
            })
            // Fazer com pollEnrich
            .pollEnrich().simple("http4://localhost:9091/retornarValor/${exchangeProperty.score}?bridgeEndpoint=true")
                .aggregationStrategy(new AgregacaoValor("valor"))
            .pollEnrich().simple("http4://localhost:9091/retornarStatus/${exchangeProperty.score}?bridgeEndpoint=true")
                .aggregationStrategy(new AgregacaoValor("status"))
        .end();
    }
}
