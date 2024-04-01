package kw.kng.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kw.kng.entity.TableEmployee;

@Repository
public class TableEmployeeDaoImpl 
{
	@PersistenceContext
	private EntityManager em;
	
	public List<TableEmployee> getAllTableEmployeeRecords()
	{
		return em.createNamedQuery("getAllTableEmployeeRecords", TableEmployee.class).getResultList();
	}
	
}
