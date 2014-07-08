package controllers;

import java.util.List;

import models.Account;
import daos.AccountDAO;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.impl.AccountServiceImpl;
import views.html.accountOverview;
import views.html.accountDetail;
import views.html.accountCreate;

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
	
	public static Result updateAccount()
	{
		return null;
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
			flash("registration.successful", "user.created-successfully");
			return redirect(routes.ApplicationController.getAccountOverview());
		}
	}
	
	public static Result gotoCreateAccount()
	{
		return ok(accountCreate.render(Form.form(Account.class)));
	}
	
	
}
