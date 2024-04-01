package kw.kng.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="tb_employee")
@NoArgsConstructor
//@NamedNativeQuery( 	name="getAllTableEmployeeRecords",
//									 	query="select * from helloworld.tb_employee order by id desc" ,
//									 	resultClass=TableEmployee.class)
public class TableEmployee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String name;
	
	private String location;

}
