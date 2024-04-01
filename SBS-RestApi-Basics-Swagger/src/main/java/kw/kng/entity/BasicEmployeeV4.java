package kw.kng.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kw.kng.request.EmployeeRequestV2;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="basic_employee_v4")
@NoArgsConstructor
public class BasicEmployeeV4
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="employee")
	private List<BasicDepartmentV4> departments;
	
	public BasicEmployeeV4(EmployeeRequestV2 req)
	{
		this.name=req.getName();
	}
	
}

/*
 
//ONE TO MANY MAPPING BI DIRECTRIONAL
------------------------------
One employee can belong to many departments scenario



 */



