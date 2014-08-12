package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;




@Entity
public class Sale extends BaseModel{

	
	@ManyToOne
	private SalesProduct salesProduct;
	
	@ManyToOne
	private Account seller;
	
	private Date sellDate;
	
	private BigDecimal price;
	
	private long amount; //Stückanzahl

	public SalesProduct getSalesProduct()
	{
		return salesProduct;
	}

	public void setSalesProduct(SalesProduct salesProduct)
	{
		this.salesProduct = salesProduct;
	}

	public Account getSeller()
	{
		return seller;
	}

	public void setSeller(Account seller)
	{
		this.seller = seller;
	}

	public Date getSellDate()
	{
		return sellDate;
	}

	public void setSellDate(Date sellDate)
	{
		this.sellDate = sellDate;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public long getAmount()
	{
		return amount;
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}
}
