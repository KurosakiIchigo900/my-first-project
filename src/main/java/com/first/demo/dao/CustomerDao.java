package com.first.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.first.demo.enitity.Customer;
import com.first.demo.exception.CustomerNotFound;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CustomerDao {
	public static Log log = LogFactory.getLog(CustomerDao.class);
	private final String SELECT = "select * from customer where id=?";
	private final String UPDATE = "UPDATE customer set name=:name,email=:email,age=:age,sex=:sex where id=:id;";
	private final String DELETE = "Delete from customer where id=:id;";

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	private final NamedParameterJdbcTemplate namedparameterjdbctemplate;

	public Optional<Customer> getById(int id) {
		try {
			// return Optional.ofNullable(jdbcTemplate.queryForObject(Q, Object[] {id},new
			// Rowmapper<Customer>());
			return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT, new Object[] { id },
					new BeanPropertyRowMapper<Customer>(Customer.class)));
		} catch (DataAccessException e) {
			return Optional.empty();
		}

	}

	public void updateById(int id, Customer customer) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", customer.getName());
		params.put("email", customer.getEmail());
		params.put("age", customer.getAge());
		params.put("sex", customer.getSex());
		params.put("id", id);
		try {
			int rows = namedparameterjdbctemplate.update(UPDATE, params);
			if (rows > 0)
				log.info("updated the rows with id ::::" + id);

		} catch (CustomerNotFound e) {
			log.error("cloudnt the rows with id ::::" + id);
			throw new CustomerNotFound("couldnt update the table for the id " + id);
		}

	}

	public void deleteById(int id) {

		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		int rows = namedparameterjdbctemplate.update(DELETE, params);
		if (rows > 0)
			log.info("ROWS DELETED SUCCESSFULLY :::::" + id);
		else {
			log.error("couldnt delete the rows for the id" + id);
			throw new DataAccessResourceFailureException("IDS ARE NOT PRESENT");

		}
	}
}
