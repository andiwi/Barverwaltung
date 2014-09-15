package services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import daos.RawProductDAO;
import daos.SalesProductDAO;
import models.Account;
import models.MapRawProductValue;
import models.RawProduct;
import models.Sale;
import services.AccountService;
import services.SaleService;

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
			salesProductDAO.persist(sale);
			
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
