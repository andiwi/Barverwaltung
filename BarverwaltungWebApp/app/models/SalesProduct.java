package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class SalesProduct extends BaseModel
{
	public enum EatOrDrink {
		eat, drink;
		
		public static EatOrDrink newInstance(EatOrDrink eatOrDrink) {
			  return EatOrDrink.values()[eatOrDrink.ordinal()];
			}
	}
	
	@NotNull
	private String productName;
	
	private String displayName;
	
	private BigDecimal defaultSalePrice;
	
	@OneToMany(mappedBy="salesProduct", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<MapRawProductValue> mapRawProductValue;
	
	@OneToMany(mappedBy="salesProduct")
	private List<Sale> sales;
	
	@Enumerated(value = EnumType.STRING)
	private EatOrDrink eatOrDrink;
	
	public SalesProduct() {}
	
	public SalesProduct(SalesProduct salesProduct) {
		this.productName = new String(salesProduct.getProductName());
		this.displayName = new String(salesProduct.getDisplayName());
		this.defaultSalePrice = new BigDecimal(salesProduct.getDefaultSalePrice().toString());
		this.mapRawProductValue = new ArrayList<MapRawProductValue>(salesProduct.getMapRawProductValue());
		this.sales = new ArrayList<Sale>(salesProduct.getSales());
		this.eatOrDrink = EatOrDrink.newInstance(salesProduct.getEatOrDrink());
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public String getDisplayName()
	{
		return displayName;
	}
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
	public BigDecimal getDefaultSalePrice()
	{
		return defaultSalePrice;
	}
	public void setDefaultSalePrice(BigDecimal defaultSalePrice)
	{
		this.defaultSalePrice = defaultSalePrice;
	}
	
	public List<Sale> getSales()
	{
		return sales;
	}
	public void setSales(List<Sale> sales)
	{
		this.sales = sales;
	}
	
	public List<MapRawProductValue> getMapRawProductValue()
	{
		return mapRawProductValue;
	}
	public void setMapRawProductValue(List<MapRawProductValue> mapRawProductValue)
	{
		this.mapRawProductValue = mapRawProductValue;
	}
	/*
	public Map<RawProduct, Long> getIngredients()
	{
		Map<RawProduct, Long> ingredients = new HashMap<RawProduct, Long>();

		for(MapRawProductValue m : mapRawProductValue)
		{
			ingredients.put(m.getRawProduct(), m.getAmountRawProduct());
		}
		
		return ingredients;
	}
	
	public void setIngredients(Map<RawProduct, Long> ingredients)
	{
		List<MapRawProductValue> list = new ArrayList<MapRawProductValue>();
		
		for(RawProduct p : ingredients.keySet())
		{
			MapRawProductValue m = new MapRawProductValue();
			m.setRawProduct(p);
			m.setAmountRawProduct(ingredients.get(p));
			
			list.add(m);
		}
		
		this.mapRawProductValue = list;
	}
	*/
	public EatOrDrink getEatOrDrink() {
		return eatOrDrink;
	}
	public void setEatOrDrink(EatOrDrink eatOrDrink) {
		this.eatOrDrink = eatOrDrink;
	}
}
