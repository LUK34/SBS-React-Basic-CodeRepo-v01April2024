package kw.kng.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.dto.CategoryDto;
import kw.kng.io.CategoryRequest;
import kw.kng.io.CategoryResponse;
import kw.kng.service.CategoryService;
import lombok.RequiredArgsConstructor;

/* 
 This controller is for managing the categories
 @author -> Luke Rajan Mathew
 */


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController
{

	private final CategoryService categoryService;
	
	/*
	 API for creating the category
	 @param categoryRequest
	 @return CategoryResponse
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	// POST -> CategoryRequest -> CategoryDto -> CategoryResponse
	public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest)
	{
		//MAP -> Request via Postman `CategoryRequest` to `CategoryDto`
		CategoryDto categoryDTO = mapToCategoryDTO(categoryRequest);
		
		//save the Category in service layer
		categoryDTO = categoryService.saveCategory(categoryDTO);
		
		//MAP -> `categoryDto` ->  `CategoryResponse`
		return mapToResponse(categoryDTO);
	}
	
	
	/*
	 		API for reading the categories
	 		@return list
	 */
	@GetMapping
	//CategoryDto -> CategoryResponse
	public List<CategoryResponse> readCategories()
	{
		List<CategoryDto> listOfCategories = categoryService.getAllCategories();
		return listOfCategories.stream().map(c -> mapToResponse(c)).collect(Collectors.toList());
	}

	
	/*
 			API for deleting the category
 			@param CategoryId
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") String categoryId)
	{
		categoryService.deleteCategory(categoryId);
	}
	

	/*
	 		Mapper method for converting DTO Object to Response Object
	 		@param categoryDTO
	 		@return CategoryResponse
	 */
	// MAP -> CategoryRequest -> CategoryDto -> CategoryResponse
	private CategoryResponse mapToResponse(CategoryDto categoryDTO) 
	{
		return CategoryResponse.builder()
				.categoryId(categoryDTO.getCategoryId())
				.name(categoryDTO.getName())
				.description(categoryDTO.getDescription())
				.categoryIcon(categoryDTO.getCategoryIcon())
				.createdAt(categoryDTO.getCreatedAt())
				.updatedAt(categoryDTO.getUpdatedAt())
				.build();
	}

	/*
	 		Mapper method for converting Request Object to DTO Object
	 		@param categoryRequest
	 		@return CategoryResponse
	 */
	//Map values from `CategoryRequest` to `CategoryDto`
	private CategoryDto mapToCategoryDTO(CategoryRequest categoryRequest) 
	{
		return CategoryDto.builder()
				.name(categoryRequest.getName())
				.description(categoryRequest.getDescription())
				.categoryIcon(categoryRequest.getIcon())
				.build();
	}

	
}
