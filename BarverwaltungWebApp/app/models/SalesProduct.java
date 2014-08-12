package models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SalesProduct extends BaseModel
{
	@NotNull
	private String productName;
	
	private String displayName;
	
	private BigDecimal defaultSalePrice;
	
	@OneToOne
	private RawProduct rawProduct1;
	private long amount1;
	
	@OneToOne
	private RawProduct rawProduct2;
	private long amount2;
	
	@OneToOne
	private RawProduct rawProduct3;
	private long amount3;
	
	@OneToOne
	private RawProduct rawProduct4;
	private long amount4;
	
	@OneToOne
	private RawProduct rawProduct5;
	private long amount5;
	
	@OneToMany(mappedBy="salesProduct")
	private List<Sale> sales;
	
	
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
	public RawProduct getRawProduct1()
	{
		return rawProduct1;
	}
	public void setRawProduct1(RawProduct rawProduct1)
	{
		this.rawProduct1 = rawProduct1;
	}
	public long getAmount1()
	{
		return amount1;
	}
	public void setAmount1(long amount1)
	{
		this.amount1 = amount1;
	}
	public RawProduct getRawProduct2()
	{
		return rawProduct2;
	}
	public void setRawProduct2(RawProduct rawProduct2)
	{
		this.rawProduct2 = rawProduct2;
	}
	public long getAmount2()
	{
		return amount2;
	}
	public void setAmount2(long amount2)
	{
		this.amount2 = amount2;
	}
	public RawProduct getRawProduct3()
	{
		return rawProduct3;
	}
	public void setRawProduct3(RawProduct rawProduct3)
	{
		this.rawProduct3 = rawProduct3;
	}
	public long getAmount3()
	{
		return amount3;
	}
	public void setAmount3(long amount3)
	{
		this.amount3 = amount3;
	}
	public RawProduct getRawProduct4()
	{
		return rawProduct4;
	}
	public void setRawProduct4(RawProduct rawProduct4)
	{
		this.rawProduct4 = rawProduct4;
	}
	public long getAmount4()
	{
		return amount4;
	}
	public void setAmount4(long amount4)
	{
		this.amount4 = amount4;
	}
	public RawProduct getRawProduct5()
	{
		return rawProduct5;
	}
	public void setRawProduct5(RawProduct rawProduct5)
	{
		this.rawProduct5 = rawProduct5;
	}
	public long getAmount5()
	{
		return amount5;
	}
	public void setAmount5(long amount5)
	{
		this.amount5 = amount5;
	}
	public List<Sale> getSales()
	{
		return sales;
	}
	public void setSales(List<Sale> sales)
	{
		this.sales = sales;
	}
	
}
