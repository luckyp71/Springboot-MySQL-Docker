package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.app.mapper.EmployeeRowMapper;
import com.example.app.model.EmployeeModel;

@Component
public class EmployeeService {

	@Autowired
	@Qualifier("mySqljdbcTemplate")
	private JdbcTemplate mySqljdbcTemplate;

	public List<EmployeeModel> getAllEmployees() {
		List<EmployeeModel> employees;
		String sql = "SELECT * FROM employee;";
		employees = mySqljdbcTemplate.query(sql, new EmployeeRowMapper());
		return employees;
	}

	public EmployeeModel getEmployee(String id) {
		EmployeeModel employee = mySqljdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = " + Integer.parseInt(id), new EmployeeRowMapper());
		return employee;
	}
	
	
	public void addEmployee(EmployeeModel employee) {
		mySqljdbcTemplate.update("INSERT INTO employee VALUES(?,?,?,?)",
				new Object[] {employee.getId(), employee.getfName(),employee.getlName(),employee.getGender()});
	}
	
	public void updateEmployee(EmployeeModel employee) {
		mySqljdbcTemplate.update("UPDATE employee SET fname=?, lname = ?, gender =? WHERE id = ?",
				new Object[] {employee.getfName(), employee.getlName(), employee.getGender(), employee.getId()});
	}
	
	public void deleteEmployee(String id) {
		mySqljdbcTemplate.update("DELETE FROM employee WHERE id=?", new Object[] {Integer.parseInt(id)});
	}

}
