package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Customer extends BaseModel
{
	public enum Gender {
		male, female
	}
		
	private String firstName;
	private String lastName;
	
	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	
	private String userName;
	private String password;
	
	
	@OneToOne(fetch = FetchType.LAZY,
				mappedBy = "boughtFrom")
	private Product boughtProduct;
	
	@OneToMany(mappedBy = "seller")
	private List<Sale> selledProducts; //Produkte die an der Bar verkauft wurden
	
	@OneToMany(mappedBy = "purchaser")
	private List<Sale> boughtProducts; //Produkte die an der Bar gekauft wurden
	
	
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
}
