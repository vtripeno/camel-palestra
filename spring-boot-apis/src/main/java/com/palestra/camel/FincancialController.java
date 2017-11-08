package com.palestra.camel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * @author victor
 *
 */
@RestController
public class FincancialController {

	@Autowired
    RestTemplate restTemplate;

	@RequestMapping("/consultScore")
	public String consultScore(@RequestBody Financial financial) {
		
		String uri = "http://localhost:9090/orquestrador";
		
		//RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Financial> request = new HttpEntity<Financial>(financial, headers);
		
		return restTemplate.postForObject(uri, request, String.class);
		
	}

	@RequestMapping(value = "/retornarValor/{score}", method = RequestMethod.GET)
	public String retornarValor(@PathVariable String score) throws Exception {
		String resultado = "";
    	try {
	    	Long scoreL = Long.valueOf(score);
	    	switch(scoreL.intValue()) {
				case 1:
					resultado = "Entre R$ 1000,00 e R$4000,00";
					break;
				case 2:
					resultado = "Entre R$ 4000,01 e R$8000,00";
					break;
				case 3:
					resultado = "Entre R$ 8000,01 e R$12000,00";
					break;
				case 4:
					resultado = "Entre R$ 12000,01 e R$16000,00";
					break;
				case 5:
					resultado = "Entre R$ 16000,01 e R$20000,00";
					break;
				default:
					throw new Exception("DEU RUIM - Score não disponível");
			}
    	} catch(Exception e) {
    		throw new Exception("DEU RUIM - Score deve ser sempre numérico");
		}
        return resultado;
	}

    @RequestMapping(value = "/retornarStatus/{score}", method = RequestMethod.GET)
    public String retornarStatus(@PathVariable String score) throws Exception {
    	String resultado = "";
    	try {
	    	Long scoreL = Long.valueOf(score);
	    	switch(scoreL.intValue()) {
				case 1:
					resultado = "Basic";
					break;
				case 2:
					resultado = "Advanced";
					break;
				case 3:
					resultado = "Master";
					break;
				case 4:
					resultado = "VIP";
					break;
				case 5:
					resultado = "Personalit";
					break;
				default:
					throw new Exception("DEU RUIM - Score não disponível");
			}
    	} catch(Exception e) {
    		throw new Exception("DEU RUIM - Score deve ser sempre numérico");
		}
        return resultado;
    }


}
