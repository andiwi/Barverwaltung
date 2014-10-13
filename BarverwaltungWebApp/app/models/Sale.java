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
	
	@ManyToOne
	private Account consumer;
	
	private Date sellDate;
	
	private BigDecimal price;
	
	private int amount; //Stückanzahl
	
	private String note;

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

	public Account getConsumer()
	{
		return consumer;
	}

	public void setConsumer(Account consumer)
	{
		this.consumer = consumer;
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

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
