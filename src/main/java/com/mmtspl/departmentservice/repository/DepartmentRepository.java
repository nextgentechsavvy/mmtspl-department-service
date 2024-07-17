package com.mmtspl.departmentservice.repository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.mmtspl.departmentservice.exception.DepartmentInfoByIDNotFoundException;
import com.mmtspl.departmentservice.exception.DepartmentInfoByNameNotFoundException;
import com.mmtspl.departmentservice.exception.NoDepartmentDataFoundException;
import com.mmtspl.departmentservice.model.Department_Master;
import com.mmtspl.departmentservice.exception.RecordNotFoundNullPointerException;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	private String message ="";
	private Boolean matched = false;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ****************** Calling from FrontController ********************** //

	@SuppressWarnings("unchecked")
	public List<Department_Master> getAllDepartment() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Department_Master> departmentList = null;
		try {
			departmentList = session.createQuery("from Department_Master").list();
		}catch(NoDepartmentDataFoundException ndf) {
			ndf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			//session.close();
		}
		return departmentList;
	}

	public Department_Master getDepartmentByID(int departmentId) {
		Session session = this.sessionFactory.getCurrentSession();
		Department_Master department = null;
		try {
			department = session.get(Department_Master.class, departmentId);
		}catch(DepartmentInfoByIDNotFoundException dIBIDnf) {
			dIBIDnf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return department;
	}

	public Department_Master addDepartment(Department_Master department) {
		Session session = this.sessionFactory.getCurrentSession();
		Serializable iStatus = session.save(department);
		return department;
	}
	
	public void updateDepartment(Department_Master department) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(department);
		session.update(department);
	}
	
	@Transactional
	public void updateDepartmentById(Department_Master department, int departmentId) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(department);
		session.saveOrUpdate(String.valueOf(departmentId), department);
	}
	
	public boolean deleteDepartment(int departmentId) {
		Session session = this.sessionFactory.getCurrentSession();
		//Transaction transaction = session.beginTransaction();
		Department_Master department_master = (Department_Master) session.get(Department_Master.class, departmentId);
		
		try {
			if (department_master != null) {
				matched = true;
				System.out.println(department_master.getDepartmentId());
				session.delete(department_master);
				message = "Deleted Department Record is:" + " \n" + "Department ID: " + department_master.getDepartmentId() + "  Department Name: " + department_master.getDepartmentName() + "  Department Location: " + department_master.getDepartmentLocation() + "  Employee ID: " + department_master.getEmployeeId() + "\n"; 
			}else{
				matched = false;
				message = "Department with ID : " + departmentId + " not found....";
			}
		}catch(DepartmentInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			//transaction.commit();
		}
		
		System.out.println("Delete message :: " + message);
		return matched;
	}
	
	// ****************** @NamedQueries ********************** //
	@SuppressWarnings("unchecked")
	public List<Department_Master> getDepartmentByName(String departmentName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Employee> employeeOptional = null;
		List<Department_Master> departmentList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Department_By_Name");
				query.setParameter("departmentName", departmentName);
				System.out.println("Named Query is : " + query.getQueryString());
				
				departmentList = (List<Department_Master>) query.list().stream().collect(Collectors.toList());
				//employeeList = session.createQuery(query.toString()).list();
				
				if(departmentList.isEmpty())
					departmentList = null;
		}catch(DepartmentInfoByNameNotFoundException enfbn) {
			enfbn.printStackTrace();
		}finally {
			//session.close();
		}
		
		return departmentList;
	}
	
	// ****************** Calling from FrontController ********************** //

	
}
