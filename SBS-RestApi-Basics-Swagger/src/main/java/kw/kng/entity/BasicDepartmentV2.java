package kw.kng.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="basic_department_v2")
public class BasicDepartmentV2
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private  BasicEmployeeV2 employee;
}

/*
 
//MANY TO ONE MAPPING at BasicDepartmentV2 (UNI DIRECTIONAL)
------------------------------

Database side:
-------------------------------------

id 		name 			employee_id
-----------------------------------------------
1			CSE					1
2			CE					1
3 			CSE					2
4			CE					2
5			ME					2





 */