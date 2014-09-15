package controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Purchase;
import models.Account.Gender;
import models.SalesProduct;
import daos.AccountDAO;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.ProductService;
import services.impl.AccountServiceImpl;
import services.impl.ProductServiceImpl;
import views.html.account.*;

public class AccountController extends ApplicationController
{
	@Transactional
	public static Result getAccount(int id)
	{
		AccountService accountService = new AccountServiceImpl();
		ProductService productService = new ProductServiceImpl();
		
		Account selectedAccount = accountService.findAccountById(id);
		
		List<Account> accounts = accountService.getAllAccounts();
		List<SalesProduct> salesProductList = productService.getAllSalesProducts();
		
		return ok(accountTemplate.render(accounts, selectedAccount, null, salesProductList));
	}
	
	@Transactional
	public static Result updateAccount()
	{
		Form<Account> form = Form.form(Account.class).bindFromRequest();
		AccountService accountService = new AccountServiceImpl();
		
		if (form.hasErrors())
		{
			int sentAccountId = Integer.valueOf(form.data().get("accountId"));
			Account selectedAccount = accountService.findAccountById(sentAccountId);
			
			List<Account> accounts = accountService.getAllAccounts();
			return badRequest(accountTemplate.render(accounts, selectedAccount, form, null));
		} else
		{
			Account account = form.get();
			
			accountService.updateAccount(account);
			flash("update.successful", "account.updated-successfully");
			return redirect(routes.AccountController.getAccount(-1));
		}
	}
	
	@Transactional(readOnly = true)
	public static Result gotoUpdateAccount()
	{
		DynamicForm form = Form.form().bindFromRequest();
		
		int sentAccountId = Integer.valueOf(form.data().get("accountId"));
		AccountService accountService = new AccountServiceImpl();
		
		Account selectedAccount = accountService.findAccountById(sentAccountId);
		Form<Account> accountForm = Form.form(Account.class).fill(selectedAccount);
		
		List<Account> accounts = accountService.getAllAccounts();
		
		return ok(accountTemplate.render(accounts, selectedAccount, accountForm, null));
	}
	
	@Transactional
	public static Result createAccount()
	{
		Form<Account> form = Form.form(Account.class).bindFromRequest();
		if (form.hasErrors()) {
			AccountService accountService = new AccountServiceImpl();
	    	List<Account> accounts = accountService.getAllAccounts();
	    	
			return badRequest(accountTemplate.render(accounts, null, form, null));
		} else {
			Account account = form.get();
			AccountService accountService = new AccountServiceImpl();
			accountService.createAccount(account);
			flash("create.successful", "account.created-successfully");
			
			List<Account> accounts = accountService.findAccount(account);
			if(accounts.size() == 1)
				return redirect(routes.AccountController.getAccount(accounts.get(0).getId()));
			
			return redirect(routes.AccountController.getAccount(-1));
		}
	}
	
	@Transactional
	public static Result gotoCreateAccount()
	{
		AccountService accountService = new AccountServiceImpl();
    	List<Account> accounts = accountService.getAllAccounts();
    	
		return ok(accountTemplate.render(accounts, null, Form.form(Account.class), null));
	}
	
	@Transactional
	public static Result payIn()
	{
		AccountService accountService = new AccountServiceImpl();
		
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		
		String accountIdStr = parameters.get("accountId")[0];
		int sentAccountId = Integer.parseInt(accountIdStr);
		Account selectedAccount = accountService.findAccountById(sentAccountId);
		
		
		
		String amountStr = parameters.get("amount")[0];
		double amountD = Double.parseDouble(amountStr);
		BigDecimal amount = BigDecimal.valueOf(amountD);
	
		if(amount.compareTo(BigDecimal.ZERO) < 0) //Wenn versucht wurde ein Negativer Betrag einzuzahlen
		{
			return badRequest("Beim einzahlen ist ein Fehler aufgetreten");
		}else
		{
			accountService.payIn(selectedAccount, amount);
			return ok(accountService.findAccountById(selectedAccount.getId()).getAccountBalance().toString());
		}
	}
	
	@Transactional
	public static Result getGridColumnsJSON(int id)
	{
		AccountService accountService = new AccountServiceImpl();
		List<Map<String,Object>> columnList = accountService.getGridColumns();
		return ok(Json.toJson(columnList));
	}
	
	
	@Transactional
	public static Result getGridDataJSON(int id)
	{
		AccountService accountService = new AccountServiceImpl();
		List<Map<String,Object>> dataList = accountService.getGridData(id);
		return ok(Json.toJson(dataList));
	}
	
	
	
	
	
	
}
