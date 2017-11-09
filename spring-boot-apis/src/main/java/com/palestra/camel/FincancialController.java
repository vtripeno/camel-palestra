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
    	try {
	    	Long scoreL = Long.valueOf(score);
	    	switch(scoreL.intValue()) {
				case 1:
					return "Entre R$ 1000,00 e R$ 4000,00";
				case 2:
					return "Entre R$ 4000,01 e R$ 8000,00";
				case 3:
					return "Entre R$ 8000,01 e R$ 12000,00";
				case 4:
					return "Entre R$ 12000,01 e R$ 16000,00";
				case 5:
					return "Entre R$ 16000,01 e R$ 20000,00";
				default:
					return "DEU RUIM - Score não disponível";
			}
    	} catch(Exception e) {
    		throw new Exception();
		}
	}

    @RequestMapping(value = "/retornarStatus/{score}", method = RequestMethod.GET)
    public String retornarStatus(@PathVariable String score) throws Exception {
    	try {
	    	Long scoreL = Long.valueOf(score);
	    	switch(scoreL.intValue()) {
				case 1:
					return "Basic";
				case 2:
					return "Advanced";
				case 3:
					return "Master";
				case 4:
					return "VIP";
				case 5:
					return "Personalit";
				default:
					return "DEU RUIM - Score não disponível";
			}
    	} catch(Exception e) {
    		throw new Exception();
		}
    }


}
