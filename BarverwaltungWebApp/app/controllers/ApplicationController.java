package controllers;

import java.util.List;
import java.util.Map;

import models.Account;
import models.SalesProduct;
import models.User;
import play.Routes;
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
import views.html.index;
import views.html.login;
import views.html.registerUser;
import views.html.purchase.purchaseOverview;
import views.html.stock.stockOverview;
import views.html.sale.saleOverview;

public class ApplicationController extends Controller {
	
	@Transactional
	public static Result login() {
		
		UserService userService = new UserServiceImpl();
		
		if(userService.countUsers() == 0) //Wenn keine User in db dann Registrier Formular
		{
			return ok(registerUser.render());
		}else return ok(login.render()); 
	}
	
	@Transactional
	public static Result authenticate()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String username = parameters.get("username")[0];
		String password = parameters.get("password")[0];
	    
		if(username == null || password == null)
		{
			return badRequest("username == null || password == null");
		}else
		{
			User candidate = new User();
			candidate.setUsername(username);
			candidate.setPasswordClear(password);
			
			//ueberpruefe ob passwort mit db passwort übereinstimmt.
			UserService userService = new UserServiceImpl();
			
			if(userService.checkUser(candidate))
			{
				session().clear();
				session("username", username);
				return ok("/"); //redirects to "/" via ajax
			}else return badRequest("Passwort falsch");			
		}
	}
	
	@Security.Authenticated(SecureController.class)
	public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    return redirect(
	        routes.ApplicationController.login()
	    );
	}
	
	@Security.Authenticated(SecureController.class)
	public static Result index() {
		return ok(index.render());
    }

	@Security.Authenticated(SecureController.class)
    @Transactional
    public static Result getPurchaseOverview()
    {
    	ProductService service = new ProductServiceImpl();
		return ok(purchaseOverview.render(service.getAllRawProducts()));
    }
    
	@Security.Authenticated(SecureController.class)
    public static Result getStockOverview()
    {
    	return ok(stockOverview.render());
    }
	
	@Security.Authenticated(SecureController.class)
	@Transactional
	public static Result getSaleOverview()
	{
		AccountService accountService = new AccountServiceImpl();
		ProductService productService = new ProductServiceImpl();
		
		Account selectedAccount = accountService.findAccountById(1);
		
		List<Account> accounts = accountService.getAllAccounts();
		List<SalesProduct> drinks = productService.getAllDrinkSalesProducts();
		List<SalesProduct> eats = productService.getAllEatSalesProducts();
		
		return ok(saleOverview.render(selectedAccount, drinks, eats));
	}
    
	@Security.Authenticated(SecureController.class)
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
          Routes.javascriptRouter("jsRoutes",
            // Routes
            controllers.routes.javascript.PurchaseController.purchase(),
            //controllers.routes.javascript.PurchaseController.edit(),
            controllers.routes.javascript.PurchaseController.delete()
          )
        );
      }
    
    

}
