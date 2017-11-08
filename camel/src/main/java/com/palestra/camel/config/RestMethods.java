package com.palestra.camel.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestMethods extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest()
	        .post("/orquestrador")
		        .produces("application/json")
		        .consumes("application/json")
		        .toD("direct:distribuidor");
    }
}