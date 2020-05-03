package org.myself.udemy.springboot.app.zuul.filter.post;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ElapsedTimePostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		
		Long end = System.currentTimeMillis();
		Long start = (Long)req.getAttribute("startReq");
		
		Long elapsedTime = end - start;
		
		log.info("{} request to {} finished in {} milliseconds", req.getMethod(), req.getRequestURL(), elapsedTime);
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
