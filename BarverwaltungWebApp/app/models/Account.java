package models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;



@Entity
public class Account extends BaseModel
{
	public enum Gender {
		male, female
	}
		
	private String firstName;
	private String lastName;
	private BigDecimal accountBalance;
	
	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	
	private String userName;
	private String password;
	
	@OneToMany(mappedBy = "seller")
	private List<Sale> selledProducts; //Produkte die an der Bar verkauft wurden
	
	@OneToMany(mappedBy = "consumer")
	private List<Sale> consumedProducts; //Produkte die an der Bar gekauft wurden
	
	@OneToMany(mappedBy = "purchaser")
	private List<Purchase> boughtProducts; //Produkte die für die Bar gekauft wurden
	
	@OneToMany(mappedBy = "owner")
	private List<BankAccountHistory> bankAccountHistory;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<Sale> getConsumedProducts()
	{
		return consumedProducts;
	}
	public void setConsumedProducts(List<Sale> consumedProducts)
	{
		this.consumedProducts = consumedProducts;
	}
}
