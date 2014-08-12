package controllers;

import java.util.List;
import java.util.Map;

import play.Routes;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ProductService;
import services.impl.ProductServiceImpl;
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
		/*
		DynamicForm form = Form.form().bindFromRequest();
		
		String dateString = form.data().get("datefield");
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Product> productList = new ArrayList<Product>();
		List<SalesProduct> origins = service.getAllProductOrigins();
		for(SalesProduct ori : origins)
		{
			String inputStr = form.data().get(ori.getProductName() + "Count");
			if(!inputStr.isEmpty())
			{
				int input = Integer.parseInt(inputStr);
				BigDecimal price = BigDecimal.valueOf(Double.valueOf(form.data().get(ori.getProductName() + "Price")));
			
				productList.addAll(createProduct(ori, input, price, date, null));
			}
		}
		ProductService service = new ProductServiceImpl();
		service.purchase(productList);
		*/
		return ok(purchaseOverview.render());    			
	}
	
	public static Result sale()
	{
		return ok(salesOverview.render());
	}
	/*
	private static List<Product> createProduct(SalesProduct origin, int amount, BigDecimal purchasePrice, Date purchaseDate, Account boughtFrom)
	{
		
		List<Product> productList = new ArrayList<Product>();
		
		for(int i = 0; i < amount; i++)
		{
			Product product = new Product();
			product.setProductOrigin(origin);
			product.setPurchaseDate(purchaseDate);
			product.setPurchasePrice(purchasePrice);
			//product.setBoughtFrom("Max Mustermann"); TODO
			
			productList.add(product);
		}
		
		return productList;
	}
	*/
	@Transactional
	public static Result modal(String datefield)
	{
		return null;
		/*
		if(datefield.equals("new"))
		{
			List<SalesProduct> origins = service.getAllProductOrigins();
			return ok(purchaseModal.render(origins));
		}else
		{
			List<SalesProduct> origins = service.getAllProductOrigins();
			return ok(purchaseEditModal.render(origins));
		}	
		*/
	}
	
	public static Result jsRoutes() {

        response().setContentType("text/javascript");

        return ok(Routes.javascriptRouter("jsRoutes",
        		controllers.routes.javascript.PurchaseController.modal())
        );

    }

}
