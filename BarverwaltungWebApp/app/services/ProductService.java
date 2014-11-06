package services;

import java.util.List;
import java.util.Map;

import models.Purchase;
import models.RawProduct;
import models.SalesProduct;

public interface ProductService {

	//Purchases
	
	public List<Purchase> purchase(List<Purchase> purchases);
	
	public List<Purchase> getAllPurchases();

	public List<Map<String,Object>> getPurchaseTableData();
	
	public boolean deletePurchase(int id);
	
	//RawProducts

	public List<RawProduct> getAllRawProducts();
	
	public List<RawProduct> getRawProduct(RawProduct rawproduct);
	
	public void createRawProduct(RawProduct rawProduct);
	
	/**
	 * Ueberprueft ob die benötigten Mengen der RawProducts vorhanden sind.
	 * @param salesProduct
	 * @param amount
	 * @return fuer wie viele SalesProducts RawProducts vorhanden sind.
	 */
	public long controlIfRawProductsAvailable(SalesProduct salesProduct);

	public RawProduct findRawProductById(Integer id);
	
	//SalesProducts
	
	public List<SalesProduct> find(SalesProduct salesproduct);

	public List<SalesProduct> getAllSalesProducts();
	
	public List<SalesProduct> getAllDrinkSalesProducts();
	
	public List<SalesProduct> getAllEatSalesProducts();

	public void createSalesProduct(SalesProduct salesProduct);
	
	
	
	

	

	
	
	//public List<Product> updatePurchase(List<Product> products);
	
	//public List<SalesProduct> getAllProductOrigins();
	
	//public List<Date> getAllPurchaseDates();
}
