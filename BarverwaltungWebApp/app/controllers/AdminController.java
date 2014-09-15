package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.MapRawProductValue;
import models.RawProduct;
import models.SalesProduct;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.impl.ProductServiceImpl;
import views.html.administration.adminOverview;
import views.html.administration.adminSalesProduct;

public class AdminController extends Controller
{
	public static Result getAdminOverview()
	{
		return ok(adminOverview.render());
	}
	
	@Transactional
	public static Result gotoAdminSalesProduct()
	{
		ProductService productService = new ProductServiceImpl();
    	List<RawProduct> rawProducts = productService.getAllRawProducts();

		return ok(adminSalesProduct.render(Form.form(SalesProduct.class), rawProducts));
	}
	
	@Transactional
	public static Result createSalesProduct()
	{
		ProductService productService = new ProductServiceImpl();
		
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String productName = parameters.get("productName")[0];
		String displayName = parameters.get("displayName")[0];
				
		String defaultSalePriceStr = parameters.get("defaultSalePrice")[0];
		BigDecimal defaultSalePrice = new BigDecimal(defaultSalePriceStr);
		
		SalesProduct salesProduct = new SalesProduct();
		salesProduct.setProductName(productName);
		salesProduct.setDisplayName(displayName);
		salesProduct.setDefaultSalePrice(defaultSalePrice);
				
		List<MapRawProductValue> mapRawProductValueList = new ArrayList<MapRawProductValue>();
		
		for(int i = 1; i <= 10; i++)
		{
			String amountRawProductStr = parameters.get("amountRawProduct" + i)[0];
			
			if(!amountRawProductStr.isEmpty())
			{
				long amountRawProduct = Long.parseLong(amountRawProductStr);
				MapRawProductValue map = new MapRawProductValue();
				map.setAmountRawProduct(amountRawProduct);
				map.setSalesProduct(salesProduct);
				
				String rawProductIdStr = parameters.get("rawProduct" + i)[0];
				if(!rawProductIdStr.equals("0"))
				{
					Integer rawProductId = Integer.parseInt(rawProductIdStr);
					map.setRawProduct(productService.findRawProductById(rawProductId));
					mapRawProductValueList.add(map);
				}
			}
		}
		
		salesProduct.setMapRawProductValue(mapRawProductValueList);
		productService.createSalesProduct(salesProduct);
		
		return ok("Erstellen erfolgreich!");
	}
}
