package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class Sale extends BaseModel{

	
	@OneToMany(mappedBy="sale")
	private List<Product> products;
	
	@ManyToOne
	private Customer seller;
	
	@ManyToOne
	private Customer purchaser;
	
	private Date sellDate;
	
	private BigDecimal price;
	
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
