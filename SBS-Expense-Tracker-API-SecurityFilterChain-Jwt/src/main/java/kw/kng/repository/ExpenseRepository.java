package kw.kng.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> 
{

	//SELECT * FROM tbl_expenses where user_id=?
	Page<Expense> findByUserSetupId(Long userId, Pageable page);

	//SELECT * FROM tbl_expenses WHERE user_id=? AND id=?
	Optional<Expense> findByUserSetupIdAndId(Long userId,  Long expenseId);
	
	
	//SELECT * FROM tbl_expenses WHERE user_id=? and category=?
	Page<Expense> findByUserSetupIdAndCategory(Long userId, String category, Pageable page);
	
	//SELECT * FROM tbl_expenses WHERE user_id=? and name LIKE '%keyword%'
	Page<Expense> findByUserSetupIdAndNameContaining(Long userId,  String keyword,Pageable page);
	
	//SELECT * FROM tbl_expenses WHERE user_id=? and date BETWEEN 'startDate' and 'endDate'
	Page<Expense> findByUserSetupIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

}
