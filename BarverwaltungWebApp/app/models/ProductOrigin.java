package models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ProductOrigin extends BaseModel
{
	public enum DrinkOrEat {
		drink, eat
	}
	
	@NotNull
	private String productName;
	
	private String displayName;
	
	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public List<Product> getProductInstances()
	{
		return productInstances;
	}

	public void setProductInstances(List<Product> productInstances)
	{
		this.productInstances = productInstances;
	}

	@Enumerated
	private DrinkOrEat drinkOrEat;
	
	private BigDecimal defaultSalePrice;
	
	@OneToMany(mappedBy = "productOrigin")
	private List<Product> productInstances;

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public DrinkOrEat getDrinkOrEat()
	{
		return drinkOrEat;
	}

	public void setDrinkOrEat(DrinkOrEat drinkOrEat)
	{
		this.drinkOrEat = drinkOrEat;
	}

	public BigDecimal getDefaultSalePrice()
	{
		return defaultSalePrice;
	}

	public void setDefaultSalePrice(BigDecimal defaultSalePrice)
	{
		this.defaultSalePrice = defaultSalePrice;
	}
	
}
