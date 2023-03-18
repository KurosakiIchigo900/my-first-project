package com.first.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.first.demo.enitity.Customer;
import com.first.demo.service.CustomerService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class Controller {
	@Autowired
	private final CustomerService customerService;

	@GetMapping("/ping")
	public ResponseEntity<String> Ping() {

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>("OK", header, HttpStatus.OK);
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	public List<Customer> getDetails() {
		 List <Customer> response = customerService.getAll();
		return response;
	}
	
	@GetMapping("/find")
	@ResponseStatus(HttpStatus.OK)
	public Customer getId(@RequestParam (value = "id")Integer id)
	{
		return customerService.findById(id);
		
	}
	
	@PutMapping("/insert")
	public void updatebyId(@RequestBody Customer customer )
	{
		 customerService.update(customer);
		
	}
	
	@PostMapping("/delete")
	public void deletebyId(@RequestParam Integer id)
	{
		customerService.deletebyId(id);
	}
}
