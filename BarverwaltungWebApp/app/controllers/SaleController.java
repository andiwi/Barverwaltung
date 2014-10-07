package controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Sale;
import models.SalesProduct;
import models.User;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.ProductService;
import services.SaleService;
import services.UserService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.SaleServiceImpl;
import services.impl.UserServiceImpl;

public class SaleController extends Controller {

	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result sale()
	{	
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
				
		String dateStr = parameters.get("datefield")[0];
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String consumerStr = parameters.get("selectedAccount_id")[0];
		
		Integer consumerId = Integer.parseInt(consumerStr);
		AccountService accountService = new AccountServiceImpl();
		Account consumer = accountService.findAccountById(consumerId);
		
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
				
		ProductService productService = new ProductServiceImpl();
		List<SalesProduct> salesProductList = productService.getAllSalesProducts();
		
		List<Sale> salesList = new ArrayList<Sale>();
		
		for(SalesProduct salesProduct : salesProductList)
		{
			String amountStr = parameters.get("amount_"+salesProduct.getId())[0];
			
			Integer amount = Integer.parseInt(amountStr);
			
			if(amount > 0)
			{
				long availableAmount = productService.controlIfRawProductsAvailable(salesProduct);
				
				if(availableAmount < amount)
				{
					return badRequest("Nicht genügend Produkte im Lager");
				}
				
				Sale sale = new Sale();
				sale.setAmount(amount);
				sale.setPrice(salesProduct.getDefaultSalePrice());
				sale.setSalesProduct(salesProduct);
				sale.setSellDate(date);
				sale.setSeller(userAccount);	
				sale.setConsumer(consumer);
				
				salesList.add(sale);
			}

		}
		
		//Überprüfung ob genügend Geld am Konto
		BigDecimal balanceAfterPurchase = accountService.checkValidity(salesList, consumerId);
		
		if(balanceAfterPurchase.compareTo(BigDecimal.ZERO) < 0)
		{
			return badRequest("Nicht genügend Geld am Konto");
		}
		
		SaleService saleService = new SaleServiceImpl();
		saleService.sell(salesList, consumer);
	    
		Map<String,Object> data = accountService.getGridDataItem(consumerId, date);
		return ok(Json.toJson(data));
	}
}
