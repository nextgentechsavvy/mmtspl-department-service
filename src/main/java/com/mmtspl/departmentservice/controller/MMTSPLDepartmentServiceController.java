package com.mmtspl.departmentservice.controller;

import java.util.List;
import javax.transaction.Transactional;
import com.mmtspl.departmentservice.model.Department_Master;
import com.mmtspl.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = {"${settings.cors_origin}"})
@CrossOrigin(origins = {"${settings.cors_origin_localhost}", "${settings.cors_origin_localhost_global}" , "${settings.cors_origin}"})
@RestController
@RequestMapping(value="/restapidepartmentservices")
public class MMTSPLDepartmentServiceController {
	
	
	@Autowired
	DepartmentService departmentService;

	//********************************* Department_Master Start *********************************************//

	@GetMapping("/departmentservices")
	public String display() {
		return "Department Services returns Hiiii...";
	}

	

	// ****************** Calling from FrontController ********************** //

	@Transactional
	@GetMapping("/getAllDepartment")
	public List<Department_Master> getAllDepartment() {
		return departmentService.getAllDepartment();
	}

	@Transactional
	@GetMapping("/getAllDepartmentEmployeeID")
	public List<Integer> getAllDepartmentEmployeeID() {
		return departmentService.getAllDepartmentEmployeeID();
	}

	@Transactional
	@GetMapping("/getDepartmentByEmployeeID/{employeeId}")
	public List<Department_Master> getDepartmentByEmployeeID(@PathVariable int employeeId) {
		return departmentService.getDepartmentByEmployeeID(employeeId);
	}

	@Transactional
	@GetMapping("/getDepartmentById/{departmentId}")
	public Department_Master getDepartmentByID(@PathVariable int departmentId) {
		return departmentService.getDepartmentByID(departmentId);
	}

 	@Transactional
	@PostMapping("/addDepartment")
	public Department_Master addDepartment(@RequestBody Department_Master department) {
		return departmentService.addDepartment(department);
	}

	@Transactional
	@PutMapping("/updateDepartment")
	public void updateDepartment(@RequestBody Department_Master department) {
		departmentService.updateDepartment(department);
	}
	
	@Transactional
	@PutMapping("/updateDepartmentById/{departmentId}")
	public void updateDepartmentById(@RequestBody Department_Master department, @PathVariable int departmentId) {
		departmentService.updateDepartmentById(department, departmentId);
	}

	
 	@Transactional
	//@DeleteMapping("/deleteEmployee/{id}")
	@GetMapping("/deleteDepartment/{departmentId}")
	public boolean deleteDepartment(@PathVariable int departmentId) {
		return departmentService.deleteDepartment(departmentId);
	}

 	
	// ****************** @NamedQueries ********************** //
	@Transactional
	@GetMapping("/getDepartmentByName/{deptName}")
	public List<Department_Master> getDepartmentByName(@PathVariable String deptName) {
		return departmentService.getDepartmentByName(deptName.trim());
	}

	// ****************** Calling from FrontController ********************** //

	
	

 	//********************************* Department_Master End *********************************************//
	

	
}
