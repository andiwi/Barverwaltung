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
	private Account boughtFrom;
	
	@ManyToOne
	private Sale sale;
	
	private Date purchaseDate;
	
	private String note;

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ProductName getProductName() {
		return productName;
	}

	public void setProductName(ProductName productName) {
		this.productName = productName;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Account getBoughtFrom() {
		return boughtFrom;
	}

	public void setBoughtFrom(Account boughtFrom) {
		this.boughtFrom = boughtFrom;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
	
}
