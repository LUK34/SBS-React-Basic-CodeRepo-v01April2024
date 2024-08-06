package kw.kng.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="tbl_users")
@Data
public class UserSetup 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Long age;
	
	@Column(name="created_at", nullable=false, updatable=false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

}


/*
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1. What is @JsonIgnore??

@JsonIgnore is a simple yet powerful annotation for managing the visibility of properties
during the serialization and deserialization processes, helping to ensure that your data is handled securely and appropriately.

This annotation is used to mark a property or method in a Java object to be ignored by Jackson 
during serialization and deserialization processes. Serialization refers to converting a Java object into a JSON representation, 
while deserialization is the reverse process, converting JSON into a Java object.

This is particularly useful for security reasons, as you might not want sensitive information like passwords to be exposed or transmitted
in JSON form.

1.1) During Serialization: 
When converting a User object to JSON, the password field will not be included in the resulting JSON string.

1.2) During Deserialization:
If the JSON string being converted to a User object contains a password field, Jackson will ignore it, and the password property 
of the deserialized User object will not be set (it will remain null or keep its default value, if one is provided).

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



 */

