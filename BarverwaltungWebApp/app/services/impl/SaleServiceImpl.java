package services.impl;

import java.math.BigDecimal;
import java.util.List;

import models.Account;
import models.MapRawProductValue;
import models.RawProduct;
import models.Sale;
import services.AccountService;
import services.SaleService;
import daos.RawProductDAO;
import daos.SalesProductDAO;

public class SaleServiceImpl implements SaleService
{
	private SalesProductDAO salesProductDAO;
	private RawProductDAO rawProductDAO;
	
	public SaleServiceImpl()
	{
		salesProductDAO = SalesProductDAO.INSTANCE;
		rawProductDAO = RawProductDAO.INSTANCE;
	}
	
	@Override
	public void sell(List<Sale> salesList, Account consumer)
	{
		for(Sale sale: salesList)
		{
			for(int i = 0; i < sale.getAmount(); i++) //In der Datenank nur Einträge mit Amount == 1 damit man bessere Statistik Queries machen kann.
			{
				Sale copy = new Sale();
				copy.setAmount(1);
				copy.setConsumer(sale.getConsumer());
				copy.setNote(sale.getNote());
				copy.setPrice(sale.getPrice());
				copy.setSalesProduct(sale.getSalesProduct());
				copy.setSellDate(sale.getSellDate());
				copy.setSeller(sale.getSeller());
				
				salesProductDAO.persist(copy);
			}
						
			//RawProduct availableAmount verringern
			List<MapRawProductValue> ingredients = sale.getSalesProduct().getMapRawProductValue();
			
			for(MapRawProductValue m : ingredients)
			{
				RawProduct rawProduct = rawProductDAO.findEntity(m.getRawProduct().getId(), RawProduct.class);
				rawProduct.setAmount(rawProduct.getAmount() - (m.getAmountRawProduct()*sale.getAmount()));
				rawProductDAO.merge(rawProduct);
			}
			
			//Geld von Konto abziehen
			BigDecimal totalPrice = sale.getPrice().multiply(new BigDecimal(sale.getAmount()));
			AccountService accountService = new AccountServiceImpl();
			accountService.spendMoney(consumer, totalPrice);
		}
	}
}
