package security;

import java.io.IOException;

import models.User;
import play.Logger;
import play.mvc.Http.Context;
import play.mvc.Result;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import controllers.Application;

public class MyDeadboltHandler extends AbstractDeadboltHandler {

	private static final String AUTHORIZATION = "authorization";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    
	@Override
	public Result beforeAuthCheck(Context arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DynamicResourceHandler getDynamicResourceHandler(Context arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject getSubject(Context context) {
		// final AuthUserIdentity u = PlayAuthenticate.getUser(context);

		// Caching might be a good idea here
		// return User.findByAuthUserIdentity(u);
		Logger.info("headers: "+context._requestHeader().headers());
		
		String authHeader = context.request().getHeader(AUTHORIZATION);
		
		if(authHeader == null)
			return null;
		
		if(context._requestHeader().session() != null) {
			Logger.info("session: "+context._requestHeader().session());
			return new User("admin", "admin");
		}
			
			
		System.out.println("authHeader: " + authHeader);

		String auth = authHeader.substring(6);

		byte[] decodedAuth;
		String[] credString = null;
		try {
			decodedAuth = new sun.misc.BASE64Decoder().decodeBuffer(auth);
			credString = new String(decodedAuth, "UTF-8").split(":");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(credString[0].equals("admin") && credString[1].equals("admin"))
			
			return new User("admin", "admin");
		else
			return null;
	}

	@Override
	public Result onAuthFailure(Context context, String arg1) {
		// TODO Auto-generated method stub
		if(context._requestHeader().accepts("text/html"))
			return Application.login(context);
		else return unauthorized("go away!");
	}

	

}
