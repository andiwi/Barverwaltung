package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MapRawProductValue extends BaseModel {
	private long amountRawProduct;

	@ManyToOne
	private RawProduct rawProduct;

	@ManyToOne
	private SalesProduct salesProduct;

	public MapRawProductValue() {
	}

	public MapRawProductValue(MapRawProductValue mapRawProductValue) {
		this.amountRawProduct = mapRawProductValue.getAmountRawProduct();
		this.rawProduct = new RawProduct(mapRawProductValue.getRawProduct());
		this.salesProduct = new SalesProduct(
				mapRawProductValue.getSalesProduct());
	}

	public RawProduct getRawProduct() {
		return rawProduct;
	}

	public void setRawProduct(RawProduct rawProduct) {
		this.rawProduct = rawProduct;
	}

	public long getAmountRawProduct() {
		return amountRawProduct;
	}

	public void setAmountRawProduct(long amountRawProduct) {
		this.amountRawProduct = amountRawProduct;
	}

	public SalesProduct getSalesProduct() {
		return salesProduct;
	}

	public void setSalesProduct(SalesProduct salesProduct) {
		this.salesProduct = salesProduct;
	}

}
