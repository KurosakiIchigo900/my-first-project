package com.first.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.demo.dao.CustomerDao;
import com.first.demo.enitity.Customer;
import com.first.demo.exception.CustomerNotFound;
import com.first.demo.repo.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
	public static Log log = LogFactory.getLog(CustomerService.class);
	@Autowired
	private final CustomerRepository customerRepo;

	@Autowired
	private final CustomerDao customerDao;

	public List<Customer> getAll() {
		return customerRepo.findAll();
	}
	
	public void deletebyId(int id)
	{
		 customerDao.deleteById(id);
		 log.info("DELETED THE IDS :::: "+id);
	}

	public Customer findById(int id) {
		return customerDao.getById(id).orElseThrow(() -> new CustomerNotFound("no records found for the id " + id));

	}
	
	

	public void update(Customer customer) {
		 customerDao.updateById(customer.getId(), customer);
	}

}
