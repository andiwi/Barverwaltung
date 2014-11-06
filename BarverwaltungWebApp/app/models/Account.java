package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Account extends BaseModel {
	public enum Gender {
		male, female;

		public static Gender newInstance(Gender gender) {
			return Gender.values()[gender.ordinal()];
		}
	}

	private String firstName;
	private String lastName;
	private BigDecimal accountBalance;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@OneToMany(mappedBy = "seller")
	private List<Sale> selledProducts; // Produkte die an der Bar verkauft
										// wurden

	@OneToMany(mappedBy = "consumer")
	private List<Sale> consumedProducts; // Produkte die an der Bar gekauft
											// wurden

	@OneToMany(mappedBy = "purchaser")
	private List<Purchase> boughtProducts; // Produkte die für die Bar gekauft
											// wurden

	@OneToMany(mappedBy = "owner")
	private List<BankAccountHistory> bankAccountHistory;

	public Account() {
	}

	public Account(Account account) {
		this.firstName = new String(account.getFirstName());
		this.lastName = new String(account.getLastName());
		this.accountBalance = new BigDecimal(account.getAccountBalance()
				.doubleValue());
		this.gender = Gender.newInstance(account.getGender());
		this.selledProducts = new ArrayList<Sale>(account.getSelledProducts());
		this.consumedProducts = new ArrayList<Sale>(
				account.getConsumedProducts());
		this.boughtProducts = new ArrayList<Purchase>(
				account.getBoughtProducts());
		this.bankAccountHistory = new ArrayList<BankAccountHistory>(
				account.getBankAccountHistory());

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<Sale> getConsumedProducts() {
		return consumedProducts;
	}

	public void setConsumedProducts(List<Sale> consumedProducts) {
		this.consumedProducts = consumedProducts;
	}

	public List<Sale> getSelledProducts() {
		return selledProducts;
	}

	public void setSelledProducts(List<Sale> selledProducts) {
		this.selledProducts = selledProducts;
	}

	public List<Purchase> getBoughtProducts() {
		return boughtProducts;
	}

	public void setBoughtProducts(List<Purchase> boughtProducts) {
		this.boughtProducts = boughtProducts;
	}

	public List<BankAccountHistory> getBankAccountHistory() {
		return bankAccountHistory;
	}

	public void setBankAccountHistory(
			List<BankAccountHistory> bankAccountHistory) {
		this.bankAccountHistory = bankAccountHistory;
	}
}
