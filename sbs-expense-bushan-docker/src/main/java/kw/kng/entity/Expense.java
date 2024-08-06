package kw.kng.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_expenses")
@Builder
public class Expense 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String expenseId;
	
	@Column(name="expense_name")
	private String name;
	
	private String description;
	
	@Column(name="expense_amount")
	private BigDecimal amount;
	
	@ManyToOne(fetch =FetchType.LAZY, optional=false)
	@JoinColumn(name="category_id", nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	private CategoryEntity category;
	
	private Date date;	
	
	@Column(name="created_at", nullable=false, updatable=false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

	/* The below is unidirectional approach. We are not going to implement Bi-directional approach.*/
	@ManyToOne(fetch =FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserSetup userSetup;


}

/*
 
 @ManyToOne: 
 This annotation specifies that there is a many-to-one relationship between the Expense entity and another entity,  which in this case is UserSetup.
 This means that multiple Expense records can be associated with a single UserSetup. The fetch = FetchType.LAZY attribute indicates that the related UserSetup entity should be loaded lazily, 
 meaning it won't be fetched from the database until it's explicitly accessed. This can improve performance by avoiding unnecessary database operations.

@JoinColumn(name="user_id", nullable=false): 
This annotation specifies the column in the Expense table that will be used to join with the UserSetup entity.
 The name="user_id" attribute indicates that the user_id column in the Expense table is the foreign key pointing to the primary key of the UserSetup entity. 
 The nullable=false attribute means this column cannot be null, enforcing a non-optional relationship; every Expense must be associated with a UserSetup.

@OnDelete(action=OnDeleteAction.CASCADE):
 This Hibernate-specific annotation is used to configure cascade delete behavior.
 When a UserSetup entity is deleted, this configuration ensures that all associated Expense entities (i.e., those referencing the UserSetup entity through the user_id column) will also be deleted from the database.
 This helps maintain referential integrity by automatically removing related data, preventing orphan records.

@JsonIgnore:
 This annotation is used to prevent the userSetup field from being serialized or included in the JSON representation 
 when the Expense entity is converted to JSON (for example, when sending data over a RESTful API). 
 This can be useful for avoiding infinite recursion in bidirectional relationships or simply to reduce the size of the JSON payload.
 
 */

















