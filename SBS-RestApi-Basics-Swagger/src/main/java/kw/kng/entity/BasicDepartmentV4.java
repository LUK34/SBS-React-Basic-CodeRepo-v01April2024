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
@Table(name="basic_department_v4")
public class BasicDepartmentV4
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private  BasicEmployeeV4 employee;
}

/*
 
//Many to One MAPPING BI DIRECTRIONAL
------------------------------
One employee can belong to many departments scenario
Mant departments can be assigned to single employee
 */