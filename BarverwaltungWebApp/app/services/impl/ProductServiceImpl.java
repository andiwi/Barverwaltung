package services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.db.jpa.Transactional;
import daos.AccountDAO;
import daos.BankAccountHistoryDAO;
import daos.ProductDAO;
import daos.ProductOriginDAO;
import daos.PurchaseDAO;
import models.Purchase;
import models.SalesProduct;
import services.ProductService;

public class ProductServiceImpl implements ProductService {

	private PurchaseDAO dao;
	
	public ProductServiceImpl()
	{
		dao = PurchaseDAO.INSTANCE;
	}
	
	@Override
	public List<Purchase> purchase(List<Purchase> purchases)
	{
		
		for(Purchase p : purchases)
		{
			dao.persist(p);
		}
		
		return dao.findPurchasesByDate(purchases.get(0).getPurchaseDate());
	}

	@Override
	public List<Purchase> getAllPurchases()
	{
		return dao.findAll();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Override
	public List<Map<String,Object>> getAllDataForPurchaseGrid() {
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		List<Date> purchaseDates = this.getAllPurchaseDates();
		List<SalesProduct> pOrigins = this.getAllProductOrigins();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Date date : purchaseDates)
		{
			Map<String, Object> entry = new HashMap<String, Object>();
			entry.put("date", dateFormat.format(date));
			
			for(SalesProduct p: pOrigins)
			{
				long amountOfProduct = dao.findAmountOfProductAtDate(date, p);
				entry.put(p.getProductName(), amountOfProduct);
			}
			dataList.add(entry);
		}
		
		return dataList;
	}

	@Override
	public List<Date> getAllPurchaseDates()
	{
		return dao.findAllPurchaseDates();
	}

	@Override
	public List<SalesProduct> getAllProductOrigins()
	{
		return ProductOriginDAO.INSTANCE.findAll();
	}

	@Override
	public List<Map<String, Object>> getAllColumnsForPurchaseGrid()
	{
		List<Map<String,Object>> columnList = new ArrayList<Map<String,Object>>();
		
		List<SalesProduct> pOrigins = this.getAllProductOrigins();
		
		Map<String, Object> entry = new HashMap<String, Object>();
		entry.put("id", "date");
		entry.put("name", "Datum");
		entry.put("field", "date");
		
		columnList.add(entry);
		
		for(SalesProduct p : pOrigins)
		{
			entry = new HashMap<String, Object>();
			
			entry.put("id", p.getProductName());
			entry.put("name", p.getDisplayName());
			entry.put("field", p.getProductName());
			
			columnList.add(entry);
		}
		
		return columnList;
	}

	 */
}
