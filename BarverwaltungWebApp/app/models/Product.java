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

	@ManyToOne
	private ProductOrigin productOrigin;
	
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

	public ProductOrigin getProductOrigin()
	{
		return productOrigin;
	}

	public void setProductOrigin(ProductOrigin productOrigin)
	{
		this.productOrigin = productOrigin;
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
