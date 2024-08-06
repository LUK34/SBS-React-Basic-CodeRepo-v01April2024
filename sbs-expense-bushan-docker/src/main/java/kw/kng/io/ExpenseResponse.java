package kw.kng.io;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse 
{
	private String expenseId;
	private String name;
	private String description;
	private BigDecimal amount;
	private Date date;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private CategoryResponse categoryResponse;
}
