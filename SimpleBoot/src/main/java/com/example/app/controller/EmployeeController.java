package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.app.model.EmployeeModel;
import com.example.app.service.EmployeeService;

@RestController
@RequestMapping("/app")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private String resp = "{\"status\":\"ok\"}";
	
	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmployeeModel>> getEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EmployeeModel> getEmployee(@RequestParam("id") String id) {
		return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
	@ResponseBody
	public String addEmployee(@RequestParam("id")String id,@RequestParam("fname") String fname,
			@RequestParam("lname") String lname, @RequestParam("gender") String gender) {
		EmployeeModel e = new EmployeeModel();
		e.setId(Integer.parseInt(id));
		e.setfName(fname);
		e.setlName(lname);
		e.setGender(gender);
		employeeService.addEmployee(e);
		return resp;
	}


	@RequestMapping(value = "updateEmployee", method = RequestMethod.PUT)
	@ResponseBody
	public String updateEmployee(@RequestParam("id") String id, @RequestParam("fname") String fname,
			@RequestParam("lname") String lname, @RequestParam("gender") String gender) {	
		EmployeeModel emp = new EmployeeModel();
		emp.setId(Integer.parseInt(id));
		emp.setfName(fname);
		emp.setlName(lname);
		emp.setGender(gender);
		employeeService.updateEmployee(emp);
		return resp;
	}

	@RequestMapping(value="deleteEmployee", method= RequestMethod.DELETE)
	@ResponseBody
	public String deleteEmployee(@RequestParam("id")String id) {
		employeeService.deleteEmployee(id);
		return resp;
	}
}
