package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.app.model.EmployeeModel;

public class EmployeeRowMapper implements RowMapper<EmployeeModel> {

	@Override
	public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeModel emplModel = new EmployeeModel();
		emplModel.setId(rs.getInt("ID"));
		emplModel.setfName(rs.getString("FNAME"));
		emplModel.setlName(rs.getString("LNAME"));
		emplModel.setGender(rs.getString("GENDER"));
		return emplModel;
	}	
}
