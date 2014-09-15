package services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.MapRawProductValue;
import models.RawProduct;
import models.Purchase;
import models.SalesProduct;

public interface ProductService {

	public List<Purchase> purchase(List<Purchase> purchases);
	
	public List<Purchase> getAllPurchases();

	public List<Map<String,Object>> getAllDataForPurchaseGrid();

	public List<RawProduct> getAllRawProducts();
	
	public List<RawProduct> getRawProduct(RawProduct rawproduct);
	
	public boolean deletePurchase(int id);

	public List<SalesProduct> getAllSalesProducts();

	public void createSalesProduct(SalesProduct salesProduct);
	
	/**
	 * Ueberprueft ob die benötigten Mengen der RawProducts vorhanden sind.
	 * @param salesProduct
	 * @param amount
	 * @return fuer wie viele SalesProducts RawProducts vorhanden sind.
	 */
	public long controlIfRawProductsAvailable(SalesProduct salesProduct);

	public RawProduct findRawProductById(Integer id);

	
	
	//public List<Product> updatePurchase(List<Product> products);
	
	//public List<SalesProduct> getAllProductOrigins();
	
	//public List<Date> getAllPurchaseDates();
}
