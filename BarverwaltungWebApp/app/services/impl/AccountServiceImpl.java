package services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import daos.AccountDAO;
import daos.BankAccountHistoryDAO;
import models.Account;
import models.BankAccountHistory;
import services.AccountService;

public class AccountServiceImpl implements AccountService
{
	private AccountDAO dao;
	private BankAccountHistoryDAO bankDao;
	
	public AccountServiceImpl()
	{
		dao = AccountDAO.INSTANCE;
		bankDao = BankAccountHistoryDAO.INSTANCE;
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

	@Override
	public Account payIn(Account account, BigDecimal money) {
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP); //auf 2 Kommastellen runden.
		
		BigDecimal newBalance = account.getAccountBalance().add(money);
		account.setAccountBalance(newBalance);
		
		BankAccountHistory bankHistory = new BankAccountHistory();
		bankHistory.setOwner(account);
		bankHistory.setDifference(money);
		
		Date currentDate = new Date();
		bankHistory.setChangeDate(currentDate);
		
		bankDao.createEntry(bankHistory); 
		return dao.updateAccount(account);
		
	}

	
	
}
