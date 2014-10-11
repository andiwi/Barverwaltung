package controllers;

import java.util.Date;

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
import play.Play;
import play.Routes;

public class SecureController extends Security.Authenticator
{
	@Override
    public String getUsername(Context ctx) {
        
        // see if the session is expired
        String previousTick = ctx.session().get("userTime");
        if (previousTick != null && !previousTick.equals("")) {
            long previousT = Long.valueOf(previousTick);
            long currentT = new Date().getTime();
            long timeout = Long.valueOf(Play.application().configuration().getString("sessionTimeout")) * 1000 * 60;
            if ((currentT - previousT) > timeout) {
                // session expired
            	play.mvc.Controller.session().clear();
                return null;
            } 
        }

        // update time in session
        String tickString = Long.toString(new Date().getTime());
        play.mvc.Controller.session("userTime", tickString);

        return ctx.session().get("username");
    }
	
	@Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.ApplicationController.login());
    }
}
