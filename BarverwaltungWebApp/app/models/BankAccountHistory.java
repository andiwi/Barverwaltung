package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class BankAccountHistory extends BaseModel {

	private BigDecimal difference;
	private Date changeDate;
	
	@ManyToOne
	private Account owner;

	public BankAccountHistory(){
	}
	
	public BankAccountHistory(BankAccountHistory bankAccountHistory){
		this.difference = new BigDecimal(bankAccountHistory.getDifference().toString());
		this.changeDate = new Date(bankAccountHistory.getChangeDate().getTime());
		this.owner = new Account(bankAccountHistory.getOwner());
	}
	
	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}
	
}
