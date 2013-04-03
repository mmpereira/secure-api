package controllers;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class TimedAction extends Action.Simple {

	@Override
	public Result call(Context ctx) throws Throwable {
		long start = System.currentTimeMillis();
		Result result = delegate.call(ctx);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("class=" +  ctx + ";");
		sb.append("method=" +  ctx + ";");
		sb.append("timeStart=" +  start + ";");
		sb.append("timeElapsed=" +  (System.currentTimeMillis() - start) + ";");
		
		Logger.info(sb.toString());
		
		return result;
	}

}
