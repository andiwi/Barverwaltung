package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import services.impl.HashHelper;

@Entity
public class User extends BaseModel
{
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToOne
	private Account account;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPasswordEncrypted(String password)
	{
		this.password = HashHelper.createPassword(password);
	}
	
	public void setPasswordClear(String password)
	{
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
