package controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Purchase;
import models.RawProduct;
import play.Routes;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import views.html.purchaseEditModal;
import views.html.purchaseModal;
import views.html.purchaseOverview;
import views.html.salesOverview;

public class PurchaseController extends Controller
{	
	private static ProductService service = new ProductServiceImpl();
	
	@Transactional
	public static Result getPurchasesJSON()
	{
		List<Map<String,Object>> dataList = service.getAllDataForPurchaseGrid();
		return ok(Json.toJson(dataList));
	}

	@Transactional
	public static Result purchase()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String dateString = parameters.get("datefield")[0];
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String rawProductStr = parameters.get("productDropdown")[0];
		RawProduct toFind = new RawProduct();
		toFind.setDisplayName(rawProductStr);
		
		List<RawProduct> rawProductList = service.getRawProduct(toFind);
		RawProduct rawProduct = rawProductList.get(0);
		
		String amountLitreStr = parameters.get("amount")[0];
		BigDecimal amountDec = new BigDecimal(amountLitreStr);
		amountDec = amountDec.multiply(new BigDecimal(1000));
		Long amount = amountDec.longValue();
		
		String piecesStr = parameters.get("pieces")[0];
		Long pieces = Long.parseLong(piecesStr);
		
		String priceStr = parameters.get("price")[0];
		BigDecimal price = new BigDecimal(priceStr);
		
		
		Purchase purchase = new Purchase();
		purchase.setPurchaseDate(date);
		purchase.setPurchasePrice(price);
		purchase.setPurchaser(new AccountServiceImpl().findAccountById(1));
		purchase.setRawProduct(rawProduct);
		purchase.setPieces(pieces);
		purchase.setAmount(amount);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(purchase);
		List<Purchase> saved = service.purchase(purchases);
		
		if(saved == null)
		{
			return badRequest("Eintrag konnte nicht gespeichert werden");
		}
		return getPurchasesJSON();   			
	}
	
	public static Result sale()
	{
		return ok(salesOverview.render());
	}
	
	@Transactional
	public static Result edit(int id)
	{
		return ok(purchaseEditModal.render());
	}
	
	@Transactional
	public static Result delete(int id)
	{
		return ok(purchaseModal.render());
	}

}
