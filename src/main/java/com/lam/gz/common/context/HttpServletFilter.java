package com.lam.gz.common.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bo.liu01
 *
 */
public abstract class HttpServletFilter implements Filter {  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
    	
    	try {
    		if(request instanceof HttpServletRequest) {    
    			HttpServletRequest requestMultiReadWrapper = new MultiReadHttpServletRequestWrapper((HttpServletRequest) request);
				ContextHolder.initContext(requestMultiReadWrapper, response);
			    chain.doFilter(requestMultiReadWrapper, response);    
			}else{
				chain.doFilter(request, response);    
			}
		} finally {
			destroyRequestContext();
		}
    } 
    
    private void destroyRequestContext() {
    	MultiReadHttpServletRequestWrapper requestMultiReadWrapper = (MultiReadHttpServletRequestWrapper)ContextHolder.getRequest();
    	requestMultiReadWrapper.removeThreadLocalBody();
    	ContextHolder.clearCurrentContext();
    }
  
}  