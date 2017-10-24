package com.javasampleapproach.angularjs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javasampleapproach.angularjs.model.Customer;

@RestController
public class RestWebController {
	
	List<Customer> cust = new ArrayList<Customer>();
	
	@RequestMapping(value = "/getallcustomer", method = RequestMethod.GET)
	public List<Customer> getResource(){
			return cust;
	}
	
//	@RequestMapping(value="/postcustomer", method=RequestMethod.POST)
//	public String postCustomer(@RequestBody Customer customer){
//		cust.add(customer);
//		
//		return "Sucessful!";
//	}
	
	@RequestMapping(value="/postcustomer", method=RequestMethod.POST)
	public ResponseEntity<String> postCustomer(@RequestParam String firstname, @RequestParam String lastname){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("firstname", firstname);
		map.add("lastname", lastname);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8081/postcustomer", request , String.class );
		System.out.println("postCustomer : response = "+response);
		return response;
	}
}