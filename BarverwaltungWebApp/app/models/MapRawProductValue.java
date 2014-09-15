package models;

import javax.persistence.*;

@Entity
public class MapRawProductValue extends BaseModel
{
	private long amountRawProduct;
	
	@ManyToOne
	private RawProduct rawProduct;
	
	
	@ManyToOne
	private SalesProduct salesProduct;
	
	

	public RawProduct getRawProduct()
	{
		return rawProduct;
	}

	public void setRawProduct(RawProduct rawProduct)
	{
		this.rawProduct = rawProduct;
	}

	public long getAmountRawProduct()
	{
		return amountRawProduct;
	}

	public void setAmountRawProduct(long amountRawProduct)
	{
		this.amountRawProduct = amountRawProduct;
	}

	public SalesProduct getSalesProduct()
	{
		return salesProduct;
	}

	public void setSalesProduct(SalesProduct salesProduct)
	{
		this.salesProduct = salesProduct;
	}

}
