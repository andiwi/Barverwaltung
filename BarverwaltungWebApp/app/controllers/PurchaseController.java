package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.data.validation.Constraints.*;

import java.util.*;

import views.html.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import models.Account;
import models.Product;
import models.ProductOrigin;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.impl.ProductServiceImpl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import daos.BaseModelDAO;
import daos.ProductDAO;
import daos.ProductOriginDAO;

public class PurchaseController extends Controller
{	
	private static ProductService service = new ProductServiceImpl();
	
	@Transactional
	public static Result getPurchasesJSON()
	{
		Map<String, List<Map<String,Object>>> grid = getGridJSON();
		return ok(Json.toJson(grid));
	}
	
	private static Map<String, List<Map<String,Object>>> getGridJSON()
	{
		Map<String, List<Map<String,Object>>> grid = new HashMap<String, List<Map<String,Object>>>();
		
		List<Map<String,Object>> columnList = service.getAllColumnsForPurchaseGrid();
		grid.put("columns", columnList);
		
		List<Map<String,Object>> dataList = service.getAllDataForPurchaseGrid();
		grid.put("data", dataList);
		
		
		
		return grid;
	}

	@Transactional
	public static Result purchase()
	{
		DynamicForm form = Form.form().bindFromRequest();
		
		String dateString = form.data().get("datefield");
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Product> productList = new ArrayList<Product>();
		List<ProductOrigin> origins = service.getAllProductOrigins();
		for(ProductOrigin ori : origins)
		{
			String inputStr = form.data().get(ori.getProductName() + "Count");
			if(!inputStr.isEmpty())
			{
				int input = Integer.parseInt(inputStr);
				BigDecimal price = BigDecimal.valueOf(Double.valueOf(form.data().get(ori.getProductName() + "Price")));
			
				productList.addAll(createProduct(ori, input, price, date, null));
			}
		}
		ProductService service = new ProductServiceImpl();
		service.purchase(productList);
		
		return ok(purchaseOverview.render());    			
	}
	
	public static Result sale()
	{
		return ok(salesOverview.render());
	}
	
	private static List<Product> createProduct(ProductOrigin origin, int amount, BigDecimal purchasePrice, Date purchaseDate, Account boughtFrom)
	{
		List<Product> productList = new ArrayList<Product>();
		
		for(int i = 0; i < amount; i++)
		{
			Product product = new Product();
			product.setProductOrigin(origin);
			product.setPurchaseDate(purchaseDate);
			product.setPurchasePrice(purchasePrice);
			//product.setBoughtFrom("Max Mustermann"); TODO
			
			productList.add(product);
		}
		
		return productList;
	}
	
	@Transactional
	public static Result modal(String datefield)
	{
		if(datefield.equals("new"))
		{
			List<ProductOrigin> origins = service.getAllProductOrigins();
			return ok(purchaseModal.render(origins));
		}else
		{
			List<ProductOrigin> origins = service.getAllProductOrigins();
			return ok(purchaseEditModal.render(origins));
		}	
	}
	
	public static Result jsRoutes() {

        response().setContentType("text/javascript");

        return ok(Routes.javascriptRouter("jsRoutes",
        		controllers.routes.javascript.PurchaseController.modal())
        );

    }

}
