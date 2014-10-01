package services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Sale;

public interface AccountService
{
	/**
	 * List of all accounts except the Anonym Account
	 * @return
	 */
	public List<Account> getAllAccounts();
	
	public List<Account> findAccount(Account account);
	
	public Account findAccountById(int id);
	
	public Account updateAccount(Account account);
	
	public void createAccount(Account account);
	
	public Account payIn(Account account, BigDecimal money);

	/**
	 * Ueberprueft ob der Account die Einkauefe bezahlen kann.
	 * @param salesList
	 * @param consumerId 
	 * @return errechneter Kontostand nach Kauf
	 */
	public BigDecimal checkValidity(List<Sale> salesList, Integer consumerId);
	
	/**
	 * Zieht das Geld vom Konto ab.
	 * @param consumer
	 * @param totalPrice
	 */
	public void spendMoney(Account account, BigDecimal money);

	public List<Map<String, Object>> getGridColumns();

	public List<Map<String, Object>> getGridData(int id);
	
	public Map<String, Object> getGridDataItem(int consumerId, Date date);
	
	public List<Map<String, Object>> getTableData(int id);
	
	/**
	 * Liefert alle Artikel die der Account konsumiert hat.
	 * @param id
	 * @return
	 */
	public List<Sale> getAllSales(Account consumer);

}
