package controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Purchase;
import models.RawProduct;
import models.User;
import play.Routes;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.ProductService;
import services.UserService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.UserServiceImpl;
import views.html.deleteModal;
import views.html.purchase.purchaseOverview;

public class PurchaseController extends Controller
{	
	private static ProductService service = new ProductServiceImpl();
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result getPurchasesJSON()
	{
		List<Map<String,Object>> dataList = service.getPurchaseTableData();
		return ok(Json.toJson(dataList));
	}

	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result purchase()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String dateString = parameters.get("datefield")[0];
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String rawProductStr = parameters.get("productInput")[0];
		RawProduct toFind = new RawProduct();
		toFind.setDisplayName(rawProductStr);
		
		List<RawProduct> rawProductList = service.getRawProduct(toFind);
		if(rawProductList.isEmpty())
		{
			return badRequest("Produkt nicht gefunden.");
		}
		RawProduct rawProduct = rawProductList.get(0);
		
		String amountLitreStr = parameters.get("amount")[0];
		BigDecimal amountDec = new BigDecimal(amountLitreStr);
		amountDec = amountDec.multiply(new BigDecimal(1000));
		Long amount = amountDec.longValue();
		
		String piecesStr = parameters.get("pieces")[0];
		Long pieces = Long.parseLong(piecesStr);
		
		String priceStr = parameters.get("price")[0];
		BigDecimal price = new BigDecimal(priceStr);
		
		String username = session("username");
		User userToFind = new User();
		userToFind.setUsername(username);
		
		UserService userService = new UserServiceImpl();
		List<User> currentUserList = userService.findUser(userToFind);
		if(currentUserList.size() != 1)
		{
			return badRequest("User nicht gefunden.");
		}
		User currentUser = currentUserList.get(0);
		Account userAccount = currentUser.getAccount();
		
		Purchase purchase = new Purchase();
		purchase.setPurchaseDate(date);
		purchase.setPurchasePrice(price);
		purchase.setPurchaser(userAccount);
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
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result deleteModal(int id)
	{
		return ok(deleteModal.render(id));
	}
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result delete(int id)
	{
		ProductService service = new ProductServiceImpl();
		boolean success = service.deletePurchase(id);
		
		if(success)
		{
			return getPurchasesJSON();
		}else{
			return badRequest("Could not delete entry");
		}
	}

}
