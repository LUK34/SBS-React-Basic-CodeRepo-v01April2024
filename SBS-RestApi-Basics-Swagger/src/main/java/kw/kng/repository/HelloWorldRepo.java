package kw.kng.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kw.kng.entity.BasicHelloWorld;

@Repository
public interface HelloWorldRepo extends PagingAndSortingRepository<BasicHelloWorld, Long>
{

	List<BasicHelloWorld> findByName(String name);

	//SELECT * FROM TABLE WHERE NAME="hello" and LOCATION="India"
	List<BasicHelloWorld> findByNameAndLocation(String name, String Location);
	
	//SELECT * FROM TABLE WHERE NAME LIKE '%ram%'
	List<BasicHelloWorld> findByNameContainingIgnoreCase(String keyword, Sort sort);
	
	@Query("FROM BasicHelloWorld where name= :name or location= :location")
	List<BasicHelloWorld>getBasicHelloWorldByNameAndLocation(String name, String location);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM BasicHelloWorld where name=:name")
	Integer deleteBasicHelloWorldByName(String name);
	//This will return the number of records that the query ahs affected.
}


/*

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 																				// KT for I_iots
 
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


PagingAndSortingRepository is one of the repository interfaces provided by Spring Data JPA, 
and it extends CrudRepository. It provides methods for pagination and sorting. JpaRepository extends PagingAndSortingRepository 
and, in addition, provides a bunch of JPA-specific methods, such as flushing the persistence context and deleting records in a batch.

So, when you ask whether PagingAndSortingRepository has all the features of JpaRepository, the answer is no. 
JpaRepository includes all the functionalities of PagingAndSortingRepository and CrudRepository, plus additional features specific to JPA. 
Here's a brief overview:

Features of CrudRepository:
-------------------------------------------
Basic CRUD operations (Create, Read, Update, Delete)
Counting records

Additional Features of PagingAndSortingRepository:
--------------------------------------------------------------------------------------
Pagination support through Pageable
Sorting records

Additional Features of JpaRepository:
--------------------------------------------------------------------------------------
Flush and batch operations
Support for JPA EntityManager, allowing for more complex JPA-specific functionality like flushing the persistence context and applying JPA hints to queries.
Deleting an entity or entities in a batch, which can be more efficient than deleting one by one
For many applications, PagingAndSortingRepository provides enough functionality, especially if you're primarily concerned with reading data with optional pagination and sorting.
However, if you need more comprehensive data access features that leverage JPA's full power, such as batch deletes or the ability to flush the persistence context, you would opt for JpaRepository.

If your current use case is satisfied with the pagination and sorting capabilities and doesn't require the additional features offered by JpaRepository, then using PagingAndSortingRepository is appropriate.
 You can always switch to JpaRepository later if your requirements change without much hassle, thanks to the consistency in Spring Data JPA's repository abstraction.

findBy Prefix:
------------------
The findBy prefix is traditionally used in Spring Data repositories to define methods that search for entities based on some condition or criteria.
Methods starting with findBy are interpreted by Spring Data JPA to generate queries that select entities based on the criteria defined after the findBy part of the method name.
For example, a method named findByUsername(String username) would be translated into a query that selects entities where the username attribute equals the provided username parameter.

getBy Prefix:
------------------
Similarly, the getBy prefix is used to define methods that retrieve entities based on certain conditions.
 Just like findBy, the query is derived from the method name.
The getBy prefix functions the same way as findBy in terms of query generation and execution. 
The choice between using getBy or findBy is more about personal or team preference and does not impact functionality.
For instance, a method named getByUsername(String username) would result in the same query as findByUsername(String username), 
selecting entities where the username attribute matches the provided username parameter

@Modifying 
-------------------
The @Modifying annotation in Spring Data JPA is used in conjunction with @Query to indicate that the annotated query method
 should be executed as a modifying query. Modifying queries are those that change the state of the database by either updating 
 or deleting entities. This annotation is necessary because, by default, Spring Data JPA query methods are considered to be read-only 
 and are used to select data rather than modify it.


@Transactional ensures that it's executed within a transactional context





 */