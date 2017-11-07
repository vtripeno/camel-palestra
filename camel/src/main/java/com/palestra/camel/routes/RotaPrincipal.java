package com.palestra.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class RotaPrincipal extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	errorHandler(
				deadLetterChannel("direct:error")
				.logExhausted(true)
		);
    	
    	from("direct:error")
    		.setBody(constant("ERROR"))
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant("500"))
    	.end();
    	
    	
        from("direct:distribuidor")
            .multicast(new AggregationStrategy() {
                @Override
                public Exchange aggregate(Exchange exchange, Exchange exchange1) {
                	if(exchange != null) {
                		
                	}
                    return exchange1;
                }
            }, true)
            .to("","")
            .end();
    }
}

/*.multicast().parallelProcessing()
.toD("direct:chamada1")
//.toD("direct:chamada2")
//.toD("direct:chamada3")
//.toD("direct:chamada1")
//.toD("direct:chamada2")
//.toD("direct:chamada3")
.end()
.to("log:custom")
.log("${body}");
//

from("direct:qualquer")
.setProperty("meuId", constant("1"))
.setBody(constant("VALOR CHAMADA QUAlQUER 1"))
.toD("direct:agregador")
.end();

from("direct:agregador")
.aggregate(property("meuId"),(oldExchange, newExchange) -> {
System.out.println("ENTROU AGG");
if(oldExchange != null) {
	System.out.println("OLD " + oldExchange.getIn().getBody(String.class));
	System.out.println("VALOR CONCATENADO: old = " + oldExchange.getIn().getBody(String.class) + " new = " + newExchange.getIn().getBody(String.class));
}
if(newExchange != null) {
	System.out.println("NEW " + newExchange.getIn().getBody(String.class));
}
return newExchange;
}).eagerCheckCompletion().completionSize(2)
.to("log:foo")
.end();

from("direct:chamada1")
.setProperty("meuId", constant("1"))
.setBody(constant("${body}1"))
.to("socket-tcp://SOCKET:16010")
.setBody(constant("VALOR CHAMADA 1"))
.toD("direct:agregador")
.end();

from("direct:chamada2")
.setProperty("meuId", constant("2"))
.setBody(constant("${body}2"))
.to("socket-tcp://SOCKET:16010")
.setBody(constant("VALOR CHAMADA 2"))
.toD("direct:agregador")
.end();

from("direct:chamada3")
.setProperty("meuId", constant("3"))
.setBody(constant("${body}3"))
.to("socket-tcp://SOCKET:16010")
.setBody(constant("VALOR CHAMADA 3"))
.toD("direct:agregador")
.end();
}*/

