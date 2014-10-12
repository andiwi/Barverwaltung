package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Account.Gender;
import models.MapRawProductValue;
import models.RawProduct;
import models.SalesProduct;
import models.User;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.AccountService;
import services.ProductService;
import services.UserService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.UserServiceImpl;
import views.html.administration.adminOverview;
import views.html.administration.adminSalesProduct;
import views.html.administration.adminUser;
import views.html.*;

public class AdminController extends Controller
{
	@Security.Authenticated(SecureController.class)
	public static Result getAdminOverview()
	{
		return ok(adminOverview.render());
	}
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result gotoAdminSalesProduct()
	{
		ProductService productService = new ProductServiceImpl();
    	List<RawProduct> rawProducts = productService.getAllRawProducts();

		return ok(adminSalesProduct.render(Form.form(SalesProduct.class), rawProducts));
	}
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result gotoAdminUser()
	{
		AccountService accountService = new AccountServiceImpl();
		return ok(adminUser.render(accountService.getAllAccounts()));
	}
	
	@Security.Authenticated(SecureController.class)
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
	
	//TODO speziell überprüfen, falls kein user vorhanden @Security.Authenticated(SecureController.class)
	@Transactional
	public static Result createUser()
	{
		UserService userService = new UserServiceImpl();
		
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String username = parameters.get("username")[0];
		String password = parameters.get("password")[0];
		
		String selectedAccountIdStr = parameters.get("selectedAccount_id")[0];
		int selectedAccountId = Integer.parseInt(selectedAccountIdStr.substring(8));
		AccountService accountService = new AccountServiceImpl();
		Account account = accountService.findAccountById(selectedAccountId);
		
		
		
		if(!(username.isEmpty() && password.isEmpty()))
		{
			User user = new User();
			user.setUsername(username);
			user.setPasswordEncrypted(password);
			user.setAccount(account);
			if(userService.createUser(user))
			{
				return ok("Anlegen erfolgreich!");
			}else return badRequest("Username schon vorhanden");
		}
		return badRequest("Username oder Passwort nicht eingegeben");
	}
	
	//TODO speziell überprüfen, falls kein user vorhanden @Security.Authenticated(SecureController.class)
	@Transactional
	public static Result createFirstUser()
	{
		AccountService accountService = new AccountServiceImpl();
		UserService userService = new UserServiceImpl();
		
		//Anonymen Account anlegen
		Account account = new Account();
		account.setFirstName("Anonym");
		account.setLastName("Anonym");
		accountService.createAccount(account);
		
		//Account für ersten User anlegen
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String firstName = parameters.get("firstName")[0];
		String lastName = parameters.get("lastName")[0];
		String genderStr = parameters.get("gender")[0];
		Gender gender = Gender.valueOf(genderStr);
		
		Account userAccount = new Account();
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setGender(gender);
		
		accountService.createAccount(userAccount);
		
		String username = parameters.get("username")[0];
		String password = parameters.get("password")[0];
		
		if(!(username.isEmpty() && password.isEmpty()))
		{
			User user = new User();
			user.setUsername(username);
			user.setPasswordEncrypted(password);
			user.setAccount(userAccount);
			if(userService.createUser(user))
			{
				return ok("Anlegen erfolgreich!");
			}else return badRequest("Username schon vorhanden");
		}
		return badRequest("Username oder Passwort nicht eingegeben");
	}
}
