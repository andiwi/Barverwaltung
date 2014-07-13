package services.impl;

import java.util.List;

import daos.AccountDAO;
import daos.BankAccountHistoryDAO;
import daos.ProductDAO;
import models.Product;
import services.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDAO dao;
	
	public ProductServiceImpl()
	{
		dao = ProductDAO.INSTANCE;
	}
	
	@Override
	public List<Product> purchase(List<Product> products)
	{
		for(Product p : products)
		{
			dao.createProduct(p);
		}
		return dao.findProductsByDate(products.get(0).getPurchaseDate());
	}

	/*
	@Override
	public List<Product> updatePurchase(List<Product> products)
	{
		List<Product> list = dao.findProductsByDate(products.get(0).getPurchaseDate());
		
		return null;
	}
	*/

}
