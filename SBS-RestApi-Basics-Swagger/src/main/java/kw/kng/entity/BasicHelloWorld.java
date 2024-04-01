package kw.kng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="basic_hello_world")
public class BasicHelloWorld 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	//@JsonProperty("full_name")
	@NotBlank(message="Name must not be empty.")
	private String name;
	
	@Column(name="age")
	//@JsonIgnore
	private Long age=0L; //If user is not giving age set the age as 0.
	
	@Column(name="location")
	private String location;
	
	@Column(name="email")
	@Email(message="Enter valid email")
	private String email;
	
	@Column(name="department")
	@NotBlank(message="Department must not be empty.")
	private String department;

	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private Date updatedAt;
	
	
}

/*
 
 1. To keep it simple `@NotBlank` is better than `@NotEmpty` and `@NotNull`
 
 */

