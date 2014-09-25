package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Session;
import services.UserService;
import services.impl.UserServiceImpl;
import play.Routes;

public class SecureController extends Security.Authenticator
{
	@Override
    public String getUsername(Context ctx) {
        return ctx.session().get("username");
    }
	
	@Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.ApplicationController.login());
    }
}
