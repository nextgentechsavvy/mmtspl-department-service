package com.mmtspl.departmentservice.service;

import java.util.List;
import javax.transaction.Transactional;
import com.mmtspl.departmentservice.exception.DepartmentInfoByIDNotFoundException;
import com.mmtspl.departmentservice.exception.DepartmentInfoByNameNotFoundException;
import com.mmtspl.departmentservice.exception.NoDepartmentDataFoundException;
import com.mmtspl.departmentservice.model.Department_Master;
import com.mmtspl.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("departmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	private Boolean matched = false;
	
	// ****************** Calling from FrontController ********************** //

	@Transactional
	public List<Department_Master> getAllDepartment() {
		List<Department_Master> departmetList = departmentRepository.getAllDepartment(); 
		
		if(departmetList.isEmpty())
			 throw new NoDepartmentDataFoundException();
		
		return departmetList;
	}
	
	@Transactional
	public Department_Master getDepartmentByID(int departmentId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Department_Master department = departmentRepository.getDepartmentByID(departmentId);
			if(department == null) throw new DepartmentInfoByIDNotFoundException(departmentId);
		return department;
	}
	
	@Transactional
	public Department_Master addDepartment(Department_Master department) {
		return departmentRepository.addDepartment(department);
	}
	
	public void updateDepartment(Department_Master department) {
		departmentRepository.updateDepartment(department);
	}
	
	public void updateDepartmentById(Department_Master department, int departmentId) {
		departmentRepository.updateDepartmentById(department, departmentId);

	}

	@Transactional
	public boolean deleteDepartment(int departmentId) {
		matched = departmentRepository.deleteDepartment(departmentId);
		if(matched == false) throw new DepartmentInfoByIDNotFoundException(departmentId);
		return matched;
	}
	
	
	
	// ****************** @NamedQueries ********************** //
	@Transactional
	public List<Department_Master> getDepartmentByName(String departmentName) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Department_Master> departmetList = departmentRepository.getDepartmentByName(departmentName);
			if(departmetList == null) throw new DepartmentInfoByNameNotFoundException(departmentName);
		return departmetList;
	}
	
	// ****************** Calling from FrontController ********************** //


	public String getSubscriptionMessage(String user) {
		
		return "Hello "+user+", Thanks for the subscription!";
	}



}
