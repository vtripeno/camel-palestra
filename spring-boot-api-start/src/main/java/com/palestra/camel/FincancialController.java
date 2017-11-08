package com.palestra.camel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Financial> request = new HttpEntity<Financial>(financial);
		
		return restTemplate.postForObject(uri, request, String.class);
		
	}

	@RequestMapping(value = "/retornarValor/{score}", method = RequestMethod.GET)
	public Double retornarValor(@PathVariable String score) {
		return 2.00;
	}

    @RequestMapping(value = "/retornarStatus/{score}", method = RequestMethod.GET)
    public String retornarStatus(@PathVariable String score) {
        return "Personalit";
    }


}
