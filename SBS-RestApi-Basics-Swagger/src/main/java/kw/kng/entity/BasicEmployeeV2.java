package kw.kng.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kw.kng.request.EmployeeRequestV2;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="basic_employee_v2")
@NoArgsConstructor
public class BasicEmployeeV2
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	
	public BasicEmployeeV2(EmployeeRequestV2 req)
	{
		this.name=req.getName();
	}
	
}

/*
 
//MANY TO ONE MAPPING at BasicDepartmentV2  (UNI DIRECTIONAL)



Postman: POST Method
----------------------------------------
{
    "name":"Chikorita",
    "department":
     [
        "CSE","CE","ECE","EEE"
    ]
}


Database side:
-----------------------------------------------

id 			name
----------------------
1				Chitara
2				Chikorita


 */



