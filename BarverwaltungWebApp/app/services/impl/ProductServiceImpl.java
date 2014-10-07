package services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.MapRawProductValue;
import models.Purchase;
import models.RawProduct;
import models.SalesProduct;
import services.ProductService;
import daos.MapRawProductValueDAO;
import daos.PurchaseDAO;
import daos.RawProductDAO;
import daos.SalesProductDAO;

public class ProductServiceImpl implements ProductService {

	private PurchaseDAO purchaseDAO;
	private RawProductDAO rawProductDAO;
	private SalesProductDAO salesProductDAO;
	private MapRawProductValueDAO mapRawProductValueDAO;
	
	public ProductServiceImpl()
	{
		purchaseDAO = PurchaseDAO.INSTANCE;
		rawProductDAO = RawProductDAO.INSTANCE;
		salesProductDAO = SalesProductDAO.INSTANCE;
		mapRawProductValueDAO = MapRawProductValueDAO.INSTANCE;
	}
	
	@Override
	public List<Purchase> purchase(List<Purchase> purchases)
	{
		
		for(Purchase p : purchases)
		{
			purchaseDAO.persist(p);
			
			RawProduct rawproduct = rawProductDAO.find(p.getRawProduct()).get(0);
			Long amount = rawproduct.getAmount();
			amount += p.getAmount() * p.getPieces();
			rawproduct.setAmount(amount);
			
			rawProductDAO.merge(rawproduct);
		}
		
		return purchaseDAO.findPurchasesByDate(purchases.get(0).getPurchaseDate());
	}

	@Override
	public List<Purchase> getAllPurchases()
	{
		return purchaseDAO.findAll();
	}
	
	@Override
	public List<Map<String,Object>> getPurchaseTableData() {
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		List<Purchase> purchases = this.getAllPurchases();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		for(Purchase p : purchases)
		{
			Map<String, Object> entry = new HashMap<String, Object>();
			entry.put("id", p.getId());
			entry.put("date", dateFormat.format(p.getPurchaseDate()));
			entry.put("purchaser", p.getPurchaser().getFirstName() + " " + p.getPurchaser().getLastName());
			entry.put("productName", p.getRawProduct().getDisplayName());
			entry.put("amount", p.getAmount()/1000);
			entry.put("pieces", p.getPieces());
			entry.put("price", p.getPurchasePrice());
			
			dataList.add(entry);
		}
		
		return dataList;
	}

	@Override
	public List<RawProduct> getAllRawProducts()
	{
		return rawProductDAO.findAll();
	}

	@Override
	public List<RawProduct> getRawProduct(RawProduct rawproduct)
	{
		return rawProductDAO.find(rawproduct);
	}

	@Override
	public boolean deletePurchase(int id)
	{
		//Purchase löschen
		Purchase p = purchaseDAO.findEntity(id, Purchase.class);
		
		if(p != null)
		{
			
			purchaseDAO.delete(id);
						
			//RawProduct Menge anpassen
			RawProduct r = p.getRawProduct();
			if(r.getAmount() < p.getAmount())
				return false; //Löschen nicht möglich da sonst negativer Lagerbestand
			
			r.setAmount(r.getAmount()-p.getAmount());
			
			rawProductDAO.merge(r);
			return true;
			
		}else return false;
		
	}

	@Override
	public List<SalesProduct> getAllSalesProducts()
	{
		return salesProductDAO.findAll();
	}

	@Override
	public void createSalesProduct(SalesProduct salesProduct)
	{
		salesProductDAO.persist(salesProduct);
	}
	
	@Override
	public long controlIfRawProductsAvailable(SalesProduct salesProduct)
	{
		Long minAvailableAmount = Long.MAX_VALUE;
		
		List<MapRawProductValue> ingredients = salesProduct.getMapRawProductValue();
		
		
		for(MapRawProductValue m : ingredients)
		{
			RawProduct rawProduct = rawProductDAO.findEntity(m.getRawProduct().getId(), RawProduct.class);
						
			Long availableAmount = rawProduct.getAmount()/m.getAmountRawProduct();
			
			if(availableAmount < minAvailableAmount)
			{
				minAvailableAmount = availableAmount;
			}
		}
		
		return minAvailableAmount;
	}

	@Override
	public RawProduct findRawProductById(Integer id)
	{
		return rawProductDAO.findEntity(id, RawProduct.class);	
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
