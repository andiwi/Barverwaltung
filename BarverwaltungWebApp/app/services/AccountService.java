package services;

import java.util.List;

import models.Account;

public interface AccountService
{
	/**
	 * List of all accounts except the Anonym Account
	 * @return
	 */
	public List<Account> getAllAccounts();
	
	public Account findAccountById(int id);
	
	public Account updateAccount(Account account);
	
	public void createAccount(Account account);
}
