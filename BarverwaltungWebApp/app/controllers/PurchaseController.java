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

import models.Account;
import models.Product;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.impl.ProductServiceImpl;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PurchaseController extends Controller
{
	 public static Result getPurchaseOverview()
    {
		return ok(purchaseOverview.render());
    }
	 
	public static Result getPurchasesJSON()
	{
		Object arrayNode = populateGrid();
		return ok(Json.toJson(arrayNode));
	}
	 
	private static Map<String, List<Map<String,Object>>> populateGrid()
	{
		Map<String, Object> row1 = new HashMap<String, Object>();
		row1.put("id", "1");
		row1.put("stiegl", "5");
		row1.put("bergkoenig", "4");
		
		Map<String, Object> row2 = new HashMap<String, Object>();
		row2.put("id", "2");
		row2.put("stiegl", "10");
		row2.put("bergkoenig", "4");
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		dataList.add(row1);
		dataList.add(row2);
		
		Map<String, List<Map<String,Object>>> grid = new HashMap<String, List<Map<String,Object>>>();
		
		grid.put("data", dataList);
		
		return grid;
		/*
		ObjectNode gridData = Json.newObject();
		gridData.put("id", "1");
		gridData.put("stiegl", "5");
		gridData.put("bergkoenig", "4");
		
		ObjectNode gridData2 = Json.newObject();
		gridData.put("id", "2");
		gridData.put("stiegl", "10");
		gridData.put("bergkoenig", "4");
		
		ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
		arrayNode.add(gridData);
		arrayNode.ada
		arrayNode.add(gridData2);
		
		ObjectNode data = Json.newObject();
		data.put("data", arrayNode);
				
		return data;
		*/
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
		
		//String[] dateStr = dateString.split("-");
		//Date date = new Date(Integer.parseInt(dateStr[2]), Integer.parseInt(dateStr[1]) - 1, Integer.parseInt(dateStr[0]));
		
		String stieglStr = form.data().get("stieglInput");
		int stieglInput = Integer.parseInt(stieglStr);
		BigDecimal stieglPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("stieglPrice")));
		
		/*
		String bergkoenigStr = form.data().get("bergkoenigInput");
		int bergkoenigInput = Integer.valueOf(bergkoenigStr);
		BigDecimal bergkoenigPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("bergkoenigPrice")));
		
		String goesserStr = form.data().get("goesserInput");
		int goesserInput = Integer.valueOf(goesserStr);
		BigDecimal goesserPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("goesserPrice")));
		
		String wineStr = form.data().get("wineInput");
		int wineInput = Integer.valueOf(wineStr);
		BigDecimal winePrice = BigDecimal.valueOf(Double.valueOf(form.data().get("winePrice")));
		
		String colaStr = form.data().get("colaInput");
		int colaInput = Integer.valueOf(colaStr);
		BigDecimal colaPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("colaPrice")));
		
		String iceTeaPStr = form.data().get("iceTeaPInput");
		int iceTeaPInput = Integer.valueOf(iceTeaPStr);
		BigDecimal iceTeaPPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("iceTeaPPrice")));
		
		String iceTeaZStr = form.data().get("iceTeaZInput");
		int iceTeaZInput = Integer.valueOf(iceTeaZStr);
		BigDecimal iceTeaZPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("iceTeaZPrice")));
		
		String waterStr = form.data().get("waterInput");
		int waterInput = Integer.valueOf(waterStr);
		BigDecimal waterPrice = BigDecimal.valueOf(Double.valueOf(form.data().get("waterPrice")));
		*/
		List<Product> productList = new ArrayList<Product>();
		
		productList.addAll(createProduct(Product.ProductName.stieglBeer, stieglInput, stieglPrice, date, null));
		/*
		productList.addAll(createProduct(Product.ProductName.bergkoenigBeer, bergkoenigInput, bergkoenigPrice, date, null));
		productList.addAll(createProduct(Product.ProductName.goesserRadler, goesserInput, goesserPrice, date, null));
		productList.addAll(createProduct(Product.ProductName.spritzer, wineInput, winePrice, date, null)); //TODO Wein in 4 Spritzer aufteilen
		productList.addAll(createProduct(Product.ProductName.cocaCola, colaInput, colaPrice, date, null));
		productList.addAll(createProduct(Product.ProductName.iceTeaPeach, iceTeaPInput, iceTeaPPrice, date, null));
		productList.addAll(createProduct(Product.ProductName.iceTeaCitron, iceTeaZInput, iceTeaZPrice, date, null));
		productList.addAll(createProduct(Product.ProductName.mineralWater, waterInput, waterPrice, date, null));
		*/
		ProductService service = new ProductServiceImpl();
		service.purchase(productList);

    	return ok(purchaseOverview.render());		
	}
	
	public static Result sale()
	{
		return ok(salesOverview.render());
	}
	
	private static List<Product> createProduct(Product.ProductName productName, int amount, BigDecimal purchasePrice, Date purchaseDate, Account boughtFrom)
	{
		List<Product> productList = new ArrayList<Product>();
		
		for(int i = 0; i < amount; i++)
		{
			Product product = new Product();
			product.setProductName(productName);
			product.setPurchaseDate(purchaseDate);
			product.setPurchasePrice(purchasePrice);
			//product.setBoughtFrom("Max Mustermann"); TODO
			
			productList.add(product);
		}
		
		return productList;
	}
}
