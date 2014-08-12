package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Purchase extends BaseModel
{
	@OneToOne
	private RawProduct product;
	
	private long amount; //Menge pro Stück (in Milliliter)
	private long pieces; //Stückanzahl
	private BigDecimal purchasePrice; //pro Stück
	private Date purchaseDate;
	
	@ManyToOne
	private Account purchaser;
	
	

	public RawProduct getProduct()
	{
		return product;
	}

	public void setProduct(RawProduct product)
	{
		this.product = product;
	}

	public long getAmount()
	{
		return amount;
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}

	public long getPieces()
	{
		return pieces;
	}

	public void setPieces(long pieces)
	{
		this.pieces = pieces;
	}

	public BigDecimal getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	public Account getPurchaser()
	{
		return purchaser;
	}

	public void setPurchaser(Account purchaser)
	{
		this.purchaser = purchaser;
	}

	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}
	
}
