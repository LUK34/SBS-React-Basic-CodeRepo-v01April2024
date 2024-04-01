package kw.kng.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kw.kng.request.EmployeeRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="basic_employee_v3")
@NoArgsConstructor
public class BasicEmployeeV3
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JoinColumn(name="department_id")
	@OneToOne
	private BasicDepartmentV3 basicdepartmentv3;
	
	public BasicEmployeeV3(EmployeeRequest req)
	{
		this.name=req.getName();
	}
	
}

/*
 
 // ONE TO ONE MAPPING  --BI DIRECTIONAL
  -----------------------------------------------
 From  EmployeeRequest we will get name of the employee and the department name. Using the controller we will take the employee name
 and pass it to BasicEmployeeV1 through the constructor field defined
 
 Here BasicDepartmentV1 (Parent Class) and BasicEmployeeV1 (Child Class). In order to save the employee Object 
 we must first save the Department Object.
 
 If you are creating the parameterized constructor. You have to create the NoArgument constructor.

//BASIC STRUCTURE -> BASIC_EMPLOYEE_V1
------------------------------

id 		name 			department_id
----------------------------------------------------
1			Shankar				1
2			Bhushan				2
3			Bharat					3

//BASIC STRUCTURE -> BASIC_DEPARTMENT_V1
--------------------------------

id 		name
------------------------
1			CE
2			CSE
3			CSE	
4			ECE
5			ECE



 */



