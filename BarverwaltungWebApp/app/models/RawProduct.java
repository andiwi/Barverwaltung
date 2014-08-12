package models;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

public class RawProduct extends BaseModel
{
	@NotNull
	private String productName;
	private String displayName; //Anzeigename in der GUI
	private long amount; //in Milliliter
	
	@OneToMany(mappedBy="rawProduct")
	private List<Purchase> purchases;

	
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

	public long getAmount()
	{
		return amount;
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}
}
