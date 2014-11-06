package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class RawProduct extends BaseModel {
	public enum Unit {
		milliliter, gramm;

		public static Unit newInstance(Unit unit) {
			return Unit.values()[unit.ordinal()];
		}
	}

	@NotNull
	private String productName;
	private String displayName; // Anzeigename in der GUI
	private long amount;

	@Enumerated(value = EnumType.STRING)
	private Unit unit;

	@OneToMany(mappedBy = "rawProduct")
	private List<MapRawProductValue> mapRawProductValue;

	public RawProduct() {
	}

	public RawProduct(RawProduct rawProduct) {
		this.productName = new String(rawProduct.getProductName());
		this.displayName = new String(rawProduct.getDisplayName());
		this.amount = rawProduct.getAmount();
		this.unit = Unit.newInstance(rawProduct.getUnit());

		this.mapRawProductValue = new ArrayList<MapRawProductValue>(
				rawProduct.getMapRawProductValue());
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<MapRawProductValue> getMapRawProductValue() {
		return mapRawProductValue;
	}

	public void setMapRawProductValue(
			List<MapRawProductValue> mapRawProductValue) {
		this.mapRawProductValue = mapRawProductValue;
	}
}
