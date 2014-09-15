package controllers;

import java.util.List;

import models.Account;
import play.Routes;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.ProductService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import views.html.index;
import views.html.purchase.purchaseOverview;
import views.html.sale.salesOverview;
import views.html.stock.stockOverview;
import views.html.account.accountTemplate;

public class ApplicationController extends Controller {
	
	public static Result index() {
        return ok(index.render());
    }
    
    public static Result getSalesOverview()
    {
    	return ok(salesOverview.render());
    }
    @Transactional
    public static Result getPurchaseOverview()
    {
    	ProductService service = new ProductServiceImpl();
		return ok(purchaseOverview.render(service.getAllRawProducts()));
    }
    
    public static Result getStockOverview()
    {
    	return ok(stockOverview.render());
    }
    
    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
          Routes.javascriptRouter("jsRoutes",
            // Routes
            controllers.routes.javascript.PurchaseController.purchase(),
            //controllers.routes.javascript.PurchaseController.edit(),
            controllers.routes.javascript.PurchaseController.delete(),
            controllers.routes.javascript.AccountController.payIn()
          )
        );
      }
    
    

}
