package kw.kng.io;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest
{
	@NotBlank(message="Expense name must not be null / no white space /not Blank")
	@Size(min=3,message="Expense name must be at least 3 characters")
	private String name;
	
	private String description;
	
	@NotNull(message="Expense amount must not be null")
	private BigDecimal amount;
	
	@NotBlank(message="Expense Category must not be null / no white space /not Blank")
	private String categoryId;
	
	@NotNull(message="Date must not be null")
	private Date date;

}
