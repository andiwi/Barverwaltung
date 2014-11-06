package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Purchase extends BaseModel {
	@ManyToOne
	private RawProduct rawProduct;

	@ManyToOne
	private Account purchaser;

	private long amount; // Menge pro Stück (in Milliliter)
	private long pieces; // Stückanzahl
	private BigDecimal purchasePrice; // pro Stück
	private Date purchaseDate;
	private String note;

	public Purchase() {
	}

	public Purchase(Purchase purchase) {
		this.rawProduct = new RawProduct(purchase.getRawProduct());
		this.purchaser = new Account(purchase.getPurchaser());
		this.amount = purchase.getAmount();
		this.pieces = purchase.getPieces();
		this.purchasePrice = new BigDecimal(purchase.getPurchasePrice()
				.toString());
		this.purchaseDate = new Date(purchase.getPurchaseDate().getTime());
		this.note = new String(purchase.getNote());
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getPieces() {
		return pieces;
	}

	public void setPieces(long pieces) {
		this.pieces = pieces;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Account getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Account purchaser) {
		this.purchaser = purchaser;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public RawProduct getRawProduct() {
		return rawProduct;
	}

	public void setRawProduct(RawProduct rawProduct) {
		this.rawProduct = rawProduct;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
