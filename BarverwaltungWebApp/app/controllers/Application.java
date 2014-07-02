package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.accountOverview;
import views.html.index;
import views.html.purchaseOverview;
import views.html.salesOverview;
import views.html.stockOverview;

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
