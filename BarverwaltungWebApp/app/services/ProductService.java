package services;

import java.util.List;

import models.Product;

public interface ProductService {

	public List<Product> purchase(List<Product> products);
	
	//public List<Product> updatePurchase(List<Product> products);
}
