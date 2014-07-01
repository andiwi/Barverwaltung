package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Sale{

	@OneToMany(mappedBy="sale")
	private List<Product> products;
	
	@ManyToOne
	private Customer seller;
	
	@ManyToOne
	private Customer purchaser;
	
	private Date purchaseDate;
	private BigDecimal price;
}
