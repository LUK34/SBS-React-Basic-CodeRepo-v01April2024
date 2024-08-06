package kw.kng.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entity.CategoryEntity;
import kw.kng.entity.Expense;

/*
 * JPA repository for Categories entity
 * @author Luke Rajan Mathew
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> 
{
	/*
	 *  finder method to retrieve the categories by user id
	 *  @param categoryEntity
	 *  @return CategoryDTO
	 */
	List<CategoryEntity>	findByUserId(Long userId);
	
	
	/*
	 *  finder method to fetch the category by userId and categoryId
	 *  @param id, category
	 *  @return CategoryDTO
	 */
	Optional<CategoryEntity> findByUserIdAndCategoryId(Long id, String categoryId);
	
	/*
	 * It checks whethere the category is present or not by user id and category name
	 * @param name, userId
	 * @return boolean
	 */
	boolean existsByNameAndUserId(String name, Long userId);
	
	/*
	 * It checks whether the category is present or not by user id and category name
	 * @param name, userId
	 * @return Optional<CategoryEntity>
	 */
	Optional<CategoryEntity> findByName(String name);
	
	Optional<CategoryEntity> findByNameAndUserId(String name, Long userId);
	
}
