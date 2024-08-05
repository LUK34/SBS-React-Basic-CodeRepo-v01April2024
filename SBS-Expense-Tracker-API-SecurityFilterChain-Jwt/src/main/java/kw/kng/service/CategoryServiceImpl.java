package kw.kng.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kw.kng.dto.CategoryDto;
import kw.kng.dto.UserSetupDto;
import kw.kng.entity.CategoryEntity;
import kw.kng.entity.UserSetup;
import kw.kng.exceptions.ItemAlreadyExistsException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
	
	private final CategoryRepository crepo;
	private final UserSetupService us;

	/*
	 * This will list out all the categories from the database
	 */
	@Override
	public List<CategoryDto> getAllCategories()
	{
		List<CategoryEntity> list = crepo.findByUserId(us.getLoggedInUser().getId());
		return list.stream().map(ce -> mapToDto(ce)).collect(Collectors.toList());
	}
	
	/*
	 * This is for deleting the category from database
	 * @param categoryEntity
	 * @return CategoryDto
	 */
	// MAP the `CategoryEntity` -> `CategoryDto`
	private CategoryDto mapToDto(CategoryEntity categoryEntity)
	{
		return CategoryDto.builder()
							.categoryId(categoryEntity.getCategoryId())
							.name(categoryEntity.getName())
							.description(categoryEntity.getDescription())
							.categoryIcon(categoryEntity.getCategoryIcon())
							.createdAt(categoryEntity.getCreatedAt())
							.updatedAt(categoryEntity.getUpdatedAt())
							.userDTO(mapToUserDto(categoryEntity.getUser()))
							.build();
	}

	// MAP the `UserSetup` -> `UserSetupDto`
	private UserSetupDto mapToUserDto(UserSetup user)
	{
		return UserSetupDto.builder()
								.email(user.getEmail())
								.name(user.getName())
								.build();
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) 
	{
		boolean isCategoryPresent = crepo.existsByNameAndUserId(categoryDto.getName(), us.getLoggedInUser().getId());
		if(isCategoryPresent)
		{
			throw new ItemAlreadyExistsException("Category is already present for the name:  " + categoryDto.getName());
		}
		CategoryEntity newCategory = mapToEntity(categoryDto);
		newCategory = crepo.save(newCategory);
		return mapToDto(newCategory);
	}
	
	/*
	 * Mapper method to convert category dto to category entity
	 * @param categoryDTO
	 * @return CategoryEntity
	 */
	// MAP `CategoryDto` to `CategoryEntity`
	private CategoryEntity mapToEntity(CategoryDto categoryDto)
	{
		return CategoryEntity.builder()
							.name(categoryDto.getName())
							.description(categoryDto.getDescription())
							.categoryIcon(categoryDto.getCategoryIcon())
							.categoryId(UUID.randomUUID().toString())
							.user(us.getLoggedInUser())
							.build();
	}
	
	@Override
	public void deleteCategory(String categoryId)
	{
		Optional<CategoryEntity> optionalCategory = crepo.findByUserIdAndCategoryId(us.getLoggedInUser().getId(), categoryId);
		if(!optionalCategory.isPresent())
		{
			throw new ResourceNotFoundException("Category not found for the id: "+categoryId);
		}
		crepo.delete(optionalCategory.get());
	}
	
	
}
