package com.lam.gz.common.filters;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lam.gz.common.context.HttpServletFilter;

/**
 * @author bo.liu01
 *
 */
@Order(1)
@Component
@WebFilter(filterName = "httpServletRequestReplacedFilter", urlPatterns = "/*")
public class HttpServletRequestReplacedFilter extends HttpServletFilter implements Filter {  
  
    @Override  
    public void destroy() {}  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {       
    }  
  
}  