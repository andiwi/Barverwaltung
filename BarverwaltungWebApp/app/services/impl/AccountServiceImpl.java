package services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daos.AccountDAO;
import daos.BankAccountHistoryDAO;
import daos.SaleDAO;
import models.Account;
import models.BankAccountHistory;
import models.Sale;
import models.SalesProduct;
import services.AccountService;
import services.ProductService;

public class AccountServiceImpl implements AccountService
{
	private AccountDAO dao;
	private BankAccountHistoryDAO bankDao;
	private SaleDAO saleDAO;
	
	public AccountServiceImpl()
	{
		dao = AccountDAO.INSTANCE;
		bankDao = BankAccountHistoryDAO.INSTANCE;
		saleDAO = SaleDAO.INSTANCE;
	}
	
	@Override
	public List<Account> getAllAccounts()
	{
		return dao.getAllAccounts();
	}
	
	@Override
	public List<Account> findAccount(Account account)
	{
		return dao.find(account);
	}

	@Override
	public Account findAccountById(int id)
	{
		return dao.findEntity(id, Account.class);	
	}

	@Override
	public Account updateAccount(Account account)
	{
		Account oldAccount = this.findAccountById(account.getId());
		account.setAccountBalance(oldAccount.getAccountBalance());
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

	@Override
	public BigDecimal checkValidity(List<Sale> salesList, Integer consumerId)
	{
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		for(Sale s : salesList)
		{
			totalPrice = totalPrice.add((s.getPrice().multiply(new BigDecimal(s.getAmount()))));
		}
		
		Account consumer = dao.findEntity(consumerId, Account.class);
		
		return consumer.getAccountBalance().subtract(totalPrice);
	}

	public void spendMoney(Account account, BigDecimal money)
	{
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP); //auf 2 Kommastellen runden.
		
		BigDecimal newBalance = account.getAccountBalance().subtract(money);
		account.setAccountBalance(newBalance);
		
		BankAccountHistory bankHistory = new BankAccountHistory();
		bankHistory.setOwner(account);
		bankHistory.setDifference(money.multiply(new BigDecimal(-1)));
		
		Date currentDate = new Date(); //TODO Datum von kauf.
		bankHistory.setChangeDate(currentDate);
		
		bankDao.createEntry(bankHistory); 		
	}

	@Override
	public List<Map<String, Object>> getGridColumns()
	{
		List<Map<String,Object>> columnList = new ArrayList<Map<String,Object>>();
		
		ProductService productService = new ProductServiceImpl();
		List<SalesProduct> salesProducts = productService.getAllSalesProducts();
		
		Map<String, Object> date = new HashMap<String, Object>();
		date.put("id", "date");
		date.put("name", "Datum");
		date.put("field", "date");
		columnList.add(date);
		
		for(SalesProduct p : salesProducts)
		{
			Map<String, Object> entry = new HashMap<String, Object>();
			entry.put("id", p.getId());
			entry.put("name", p.getDisplayName());
			entry.put("field", p.getProductName());
						
			columnList.add(entry);
		}
		
		return columnList;
	}

	@Override
	public List<Map<String, Object>> getGridData(int id)
	{
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		List<Sale> sales = this.getAllSales(dao.findEntity(id, Account.class));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				
		for(Sale s : sales)
		{
			boolean alreadyInserted = false;
			
			if(dataList.size() > 0)
			{
				Map<String,Object> lastEntry = dataList.get(dataList.size()-1);
				
				String dateStr = dateFormat.format(s.getSellDate());
				String lastEntryDateStr = (String) lastEntry.get("date");
				
				if(lastEntryDateStr.equals(dateStr))
				{
					if(lastEntry.containsKey(s.getSalesProduct().getProductName())) //Falls das Produkt an dem Datum schon einmal gekauft wurde, aktalisiere die Menge die gekauft wurde.
					{
						int value = (int)lastEntry.get(s.getSalesProduct().getProductName());
						value += s.getAmount();
						
						lastEntry.remove(s.getSalesProduct().getProductName()); //Lösche den alten Eintrag
						lastEntry.put(s.getSalesProduct().getProductName(), value); //Füge den neuen Eintrag hinzu
						
						alreadyInserted = true;
					}else{
						lastEntry.put(s.getSalesProduct().getProductName(), s.getAmount());
						alreadyInserted = true;
					}
				}
			}
			
			if(alreadyInserted == false) //Wenn noch nicht eingefügt wurde
			{
				Map<String, Object> newEntry = new HashMap<String, Object>();
				newEntry.put("date", dateFormat.format(s.getSellDate()));
				newEntry.put("note", s.getNote());
				newEntry.put(s.getSalesProduct().getProductName(), s.getAmount());
				
				dataList.add(newEntry);
			}
		}
			
		return dataList;
	}

	@Override
	public Map<String, Object> getGridDataItem(int consumerId, Date date)
	{
		Map<String,Object> data = new HashMap<String,Object>();
		
		List<Sale> sales = this.getAllSalesByDate(dao.findEntity(consumerId, Account.class), date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Sale s : sales)
		{
			if(data.isEmpty())
			{
				data.put("date", dateFormat.format(s.getSellDate()));
				data.put(s.getSalesProduct().getProductName(), s.getAmount());
			}else{
				if(data.containsKey(s.getSalesProduct().getProductName())) //Falls das Produkt an dem Datum schon einmal gekauft wurde, aktalisiere die Menge die gekauft wurde.
				{
					int value = (int)data.get(s.getSalesProduct().getProductName());
					value += s.getAmount();
							
					data.remove(s.getSalesProduct().getProductName()); //Lösche den alten Eintrag
					data.put(s.getSalesProduct().getProductName(), value); //Füge den neuen Eintrag hinzu
							
				}else{
					data.put(s.getSalesProduct().getProductName(), s.getAmount());
				}
			}	
		}
		return data;
	}
	
	@Override
	public List<Map<String, Object>> getTableData(int id)
	{
		return this.getGridData(id);
	}
	
	private List<Sale> getAllSalesByDate(Account consumer, Date date)
	{
		return saleDAO.findAllWhereConsumerAndDate(consumer, date);
	}

	public List<Sale> getAllSales(Account consumer)
	{
		return saleDAO.findAllWhereConsumer(consumer);
	}

	
}
