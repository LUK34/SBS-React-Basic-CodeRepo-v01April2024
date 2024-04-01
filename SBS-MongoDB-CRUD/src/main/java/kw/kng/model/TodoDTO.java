package kw.kng.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class TodoDTO 
{
	@Id
	private String id;
	
	@NotNull(message="todo cannot be null")
	private String todo;
	 
	@NotNull(message="description cannot be null")
	private String description;
	
	@NotNull(message="completed cannot be null")
	private Boolean completed;
	
	private Date createdAt;
	
	private Date updatedAt;
}

/*
 1) In a Spring Boot application that uses MongoDB as the data store, the @Document annotation is used in the context of Spring Data MongoDB.
This annotation marks a class as being a domain object that should be persisted to MongoDB. 
When you annotate a class with @Document, you're indicating that instances of this class will be stored in a MongoDB collection.

2) The @Document annotation has several attributes that can be used to customize how the class is mapped to the MongoDB collection,
 but one of the most commonly used attributes is collection. By specifying the collection attribute, you can explicitly set the name of the MongoDB collection 
 where the documents (instances of the annotated class) will be stored. If you do not specify a collection name, Spring Data MongoDB will use a default name derived from the class name 
 (by converting the first letter to lowercase and using that as the collection name).
 
 
 */














