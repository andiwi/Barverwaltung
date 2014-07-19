package controllers;

import java.util.List;

import models.Account;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.impl.AccountServiceImpl;
import views.html.accountOverview;
import views.html.index;
import views.html.purchaseOverview;
import views.html.salesOverview;
import views.html.stockOverview;

public class ApplicationController extends Controller {
	
	public static Result index() {
        return ok(index.render());
    }
    
    public static Result getSalesOverview()
    {
    	return ok(salesOverview.render());
    }
    
    public static Result getPurchaseOverview()
    {
		return ok(purchaseOverview.render());
    }
    
    @Transactional
    public static Result getAccountOverview()
    {
    	AccountService accountService = new AccountServiceImpl();
    	List<Account> accounts = accountService.getAllAccounts();
    	
    	
    	return ok(accountOverview.render(accounts));
    }
    
    public static Result getStockOverview()
    {
    	return ok(stockOverview.render());
    }
    
    

}
