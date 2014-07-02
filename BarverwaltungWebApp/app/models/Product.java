package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Product extends BaseModel {

	public enum ProductName {
		stieglBeer,
		bergkoenigBeer,
		goesserRadler,
		cocaCola,
		iceTeaPeach,
		iceTeaCitron,
		mineralWater,
		spritzer,
		klopfer,
		jaegermeister,
		tequila,
		
		toast,
		pizzaSalami,
		pizzaMargarita,
		mannerSchnitten,
		pischingerEcken,
		fairtradeSchoko,
		popcorn,
		
		otherProduct
	}
	
	public enum DrinkOrEat {
		drink, eat
	}
	
	@NotNull
	private ProductName productName;
	
	@NotNull
	private BigDecimal purchasePrice;
	
	@JoinColumn(name="boughtFrom")
	@OneToOne
	private Customer boughtFrom;
	
	@ManyToOne
	private Sale sale;
	
	private Date purchaseDate;
	
	private String note;
	
	
	
}
