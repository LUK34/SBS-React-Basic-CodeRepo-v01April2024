package kw.kng.service;

import java.util.List;

import kw.kng.dto.CategoryDto;

/*
 	Service interface for managing categories
 	@author -> Luke Rajan Mathew
 */
public interface CategoryService 
{
	/*
	 * This is for reading the categories of the database
	 * @return list
	 * */
	List<CategoryDto> getAllCategories();
	
	/*
	 * This is for creating a new category
	 * @return CategoryDTO
	 */
	CategoryDto saveCategory(CategoryDto categoryDto);
	
	
	/*
	 *This is for deleting the category based on categoryId.
	 * this will not return anything.
	 */
	void deleteCategory(String categoryId);
}
