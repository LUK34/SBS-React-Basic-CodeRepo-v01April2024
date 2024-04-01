package kw.kng.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="basic_department_v1")
public class BasicDepartmentV1
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
}

/*
 
 //ONE TO ONE MAPPING
---------------------------------------
 
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