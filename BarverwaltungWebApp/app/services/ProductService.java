package services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Product;
import models.ProductOrigin;

public interface ProductService {

	public List<Product> purchase(List<Product> products);

	public List<Map<String,Object>> getAllDataForPurchaseGrid();
	
	public List<Map<String,Object>> getAllColumnsForPurchaseGrid();
	
	//public List<Product> updatePurchase(List<Product> products);
	
	public List<ProductOrigin> getAllProductOrigins();
	
	public List<Date> getAllPurchaseDates();
}
