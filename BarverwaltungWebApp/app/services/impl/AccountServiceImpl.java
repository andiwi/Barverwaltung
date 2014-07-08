package services.impl;

import java.math.BigDecimal;
import java.util.List;

import daos.AccountDAO;
import models.Account;
import services.AccountService;

public class AccountServiceImpl implements AccountService
{
	private AccountDAO dao;
	
	public AccountServiceImpl()
	{
		dao = AccountDAO.INSTANCE;
	}
	
	@Override
	public List<Account> getAllAccounts()
	{
		return dao.getAllAccounts();
	}

	@Override
	public Account findAccountById(int id)
	{
		return dao.findAccountById(id);	
	}

	@Override
	public Account updateAccount(Account account)
	{
		return dao.updateAccount(account);
	}

	@Override
	public void createAccount(Account account)
	{
		account.setAccountBalance(BigDecimal.ZERO);
		dao.createAccount(account);
	}

	
	
}
