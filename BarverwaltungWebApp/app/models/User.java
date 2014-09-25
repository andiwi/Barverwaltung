package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

import services.impl.HashHelper;

@Entity
public class User extends BaseModel
{
	@Column(unique=true)
	private String username;
	private String password;
	
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
}
