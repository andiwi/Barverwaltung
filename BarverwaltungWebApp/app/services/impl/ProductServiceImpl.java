package services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Purchase;
import services.ProductService;
import daos.PurchaseDAO;

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
	
	@Override
	public List<Map<String,Object>> getAllDataForPurchaseGrid() {
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		List<Purchase> purchases = this.getAllPurchases();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Purchase p : purchases)
		{
			Map<String, Object> entry = new HashMap<String, Object>();
			entry.put("date", dateFormat.format(p.getPurchaseDate()));
			entry.put("purchaser", p.getPurchaser().getFirstName() + " " + p.getPurchaser().getLastName());
			entry.put("productName", p.getRawProduct().getDisplayName());
			entry.put("amount", p.getAmount());
			entry.put("pieces", p.getPieces());
			entry.put("price", p.getPurchasePrice());
			
			dataList.add(entry);
		}
		
		return dataList;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	

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

	

	 */
}
