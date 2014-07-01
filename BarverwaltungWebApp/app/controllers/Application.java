package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result getSalesOverview()
    {
    	return ok(salesOverview.render());
    }
    
    public static Result getAccountOverview()
    {
    	return ok(accountOverview.render());
    }
    
    public static Result getPurchaseOverview()
    {
    	return ok(purchaseOverview.render());
    }
    
    public static Result getStockOverview()
    {
    	return ok(stockOverview.render());
    }
    
    

}
