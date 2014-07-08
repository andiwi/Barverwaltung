package controllers;

import java.math.BigDecimal;
import java.util.List;

import models.Account;
import daos.AccountDAO;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.impl.AccountServiceImpl;
import views.html.accountOverview;
import views.html.accountDetail;
import views.html.accountCreate;
import views.html.accountUpdate;

public class AccountController extends ApplicationController
{
	@Transactional
	public static Result getAccount(int id)
	{
		AccountService accountService = new AccountServiceImpl();
		Account selectedAccount = accountService.findAccountById(id);
		
		List<Account> accounts = accountService.getAllAccounts();
		
		return ok(accountDetail.render(accounts, selectedAccount));
	}
	
	@Transactional
	public static Result updateAccount()
	{
		Form<Account> form = Form.form(Account.class).bindFromRequest();
		AccountService accountService = new AccountServiceImpl();
		
		if (form.hasErrors())
		{
			int sentAccountId = Integer.valueOf(form.data().get("accountId"));
			Account account = accountService.findAccountById(sentAccountId);
			
			return badRequest(accountUpdate.render(form, account));
		} else
		{
			Account account = form.get();
			
			accountService.updateAccount(account);
			flash("update.successful", "account.updated-successfully");
			return redirect(routes.ApplicationController.getAccountOverview());
		}
	}
	
	@Transactional(readOnly = true)
	public static Result gotoUpdateAccount()
	{
		DynamicForm form = Form.form().bindFromRequest();
		
		int sentAccountId = Integer.valueOf(form.data().get("accountId"));
		AccountService accountService = new AccountServiceImpl();
		Account account = accountService.findAccountById(sentAccountId);
		
		Form<Account> accountForm = Form.form(Account.class).fill(account);
		
		return ok(accountUpdate.render(accountForm, account));
	}
	
	@Transactional
	public static Result createAccount()
	{
		Form<Account> form = Form.form(Account.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(accountCreate.render(form));
		} else {
			Account account = form.get();
			AccountService accountService = new AccountServiceImpl();
			accountService.createAccount(account);
			flash("create.successful", "account.created-successfully");
			return redirect(routes.ApplicationController.getAccountOverview());
		}
	}
	
	public static Result gotoCreateAccount()
	{
		return ok(accountCreate.render(Form.form(Account.class)));
	}
	
	@Transactional
	public static Result payIn()
	{
		AccountService accountService = new AccountServiceImpl();
		
		DynamicForm form = Form.form().bindFromRequest();
		
		int sentAccountId = Integer.parseInt((form.data().get("accountId")));
		Account selectedAccount = accountService.findAccountById(sentAccountId);
		
		double amountD = Double.parseDouble(form.data().get("amount"));
		BigDecimal amount = BigDecimal.valueOf(amountD);
		
		if(amount.compareTo(BigDecimal.ZERO) < 0) //Wenn versucht wurde ein Negativer Betrag einzuzahlen
		{
			List<Account> accounts = accountService.getAllAccounts();
			return badRequest(accountDetail.render(accounts, selectedAccount));
		}else
		{
			accountService.payIn(selectedAccount, amount);
			return getAccount(sentAccountId);
		}
	}
	
	
}
