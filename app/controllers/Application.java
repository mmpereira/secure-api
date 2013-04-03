package controllers;

import static play.data.Form.form;
import play.cache.Cached;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.With;
import views.html.index;
import views.html.login;
import be.objectify.deadbolt.java.actions.SubjectPresent;

public class Application extends Controller {
  
	public static final String USER_ROLE = "user";
	private static String previousUri = null;
	
	public static class Login {

		public String username;
		public String password;

		public String validate() {
			if (! (username.equals("admin") && password.equals("admin"))) {
				return "Invalid user or password";
			}
			return null;
		}

	}
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result login() {
    	return ok(login.render(form(Login.class)));
    }
    
    public static Result login(Context context) {
    	
    	if(request().headers().containsKey("Referer")) {
    		previousUri = request().headers().get("Referer")[0];
    	}
    	
    	return ok(login.render(form(Login.class)));
    }
    
    public static Result authenticate() {
    	Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else  {
        	session("username", loginForm.get().username);
        	if(previousUri != null)
        		return redirect(previousUri);
        	return ok("");
        }
        
    }
    
    @SubjectPresent
    @With(TimedAction.class)
    @Cached(key="publication")
    public static Result hello() {
    	return ok("hello");
    }
    
    public static Result get(Long id) {
    	return ok("coiso");
    }
  
}
