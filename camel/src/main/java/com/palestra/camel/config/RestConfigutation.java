package com.palestra.camel.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestConfigutation extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().component("spark-rest")
            // configuracao de contexto, host e porta
            .contextPath("/").host("localhost").port("9090")
            // configuracao de binding para efetuar automaticamente bind
            // json para pojo
            .bindingMode(RestBindingMode.off)
            // formatar saida com pretty print
            .dataFormatProperty("prettyPrint", "true")
            // adicao de swagger api-doc
            .apiContextPath("/api-doc/camel-palestra").apiProperty("api.title", "Camel Socket")
            .apiProperty("api.version", "1.0.0").apiProperty("cors", "true");


    }
}
