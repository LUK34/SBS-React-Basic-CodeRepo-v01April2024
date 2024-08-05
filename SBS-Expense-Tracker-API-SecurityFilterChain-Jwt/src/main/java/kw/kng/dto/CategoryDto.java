package kw.kng.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto 
{
	private String categoryId;
	private String name;
	private String description;
	private String categoryIcon;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private UserSetupDto userDTO;
	
}
