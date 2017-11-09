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

        from("direct:distribuidor").autoStartup(true).id("distribuidor")
        	// setar o http method para as próximas chamadas
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            // pegar o contrato de entrada e separar somente o elemento que interessa em uma 
            // property que existirá somente em tempo de execução
            .process(exchange -> {
                JSONObject jsonObject = new JSONObject(exchange.getIn().getBody(String.class));
                exchange.setProperty("score", jsonObject.get("score"));
                exchange.getIn().setBody(jsonObject);
            })
            // fazer chamadas de enrichment para poder enriquecer a mensagem de entrada
            .pollEnrich().simple("http4://localhost:9091/retornarValor/${exchangeProperty.score}?bridgeEndpoint=true")
                .aggregationStrategy(new AgregacaoValor("valor"))
            .pollEnrich().simple("http4://localhost:9091/retornarStatus/${exchangeProperty.score}?bridgeEndpoint=true")
                .aggregationStrategy(new AgregacaoValor("status"))
        .end();
    }
}
