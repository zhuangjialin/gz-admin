package com.lam.gz.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.lam.gz.common.config.CommonBeanConfigUtil;
import com.lam.gz.common.constant.CommonConstants;
import com.lam.gz.common.response.MsgCode;
import com.lam.gz.common.response.MsgResponse;
import com.lam.gz.common.util.HttpServletUtil;
import com.lam.gz.common.util.StringUtil;
import com.lam.gz.common.util.TokenUtil;

/**
 * @author bo.liu01
 *
 */
@Order(2)
@Component
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class AccessFilter implements Filter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//PathMatcher matcher = new AntPathMatcher()
	/**  */
	private final PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
  
    @Override  
    public void destroy() {  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setCharacterEncoding("utf-8");
	        
			if(!matcheIgnoreAuthUrl(httpRequest)){
				String token = TokenUtil.parseToken(httpRequest);
    			if(StringUtil.isBlank(token)){
    				//检查是否是feign调用
    				if(!TokenUtil.checkFeignAuth(httpRequest)){
    					httpResponse.setStatus(401);
    					HttpServletUtil.outWrite(new MsgResponse(MsgCode.UN_LOGIN_IN));
    					return ;
    				}
    			}else{
    				if(!TokenUtil.isLogin(token)){
        				logger.info("登录信息已过期! token:{}", token);
        				httpResponse.setStatus(401);
        				HttpServletUtil.outWrite(new MsgResponse(MsgCode.UN_LOGIN_IN));
        				return ;
        			}
        			httpRequest.setAttribute(CommonConstants.ACCESS_TOKEN_NAME, token);
        			
        			validateAuthority(httpRequest);
    			}
			}
		}
		chain.doFilter(request, response);    
    }
    
	private boolean matcheIgnoreAuthUrl(HttpServletRequest request) {
		if("OPTIONS".equalsIgnoreCase(request.getMethod())){
			return true;
		}
		String ignoreAuthUrl = CommonBeanConfigUtil.getConfig().getAccessFilterIgnoreAuthUrl();
		if(StringUtil.isBlank(ignoreAuthUrl)){
			return false;
		}
		String path = TokenUtil.getPath(request);
		String[] urls = ignoreAuthUrl.split(";");
		for (String urlReg : urls) {
			if(StringUtil.isBlank(urlReg)){
				continue;
			}
			if(resourceLoader.getPathMatcher().match(urlReg, path)){
				return true;
			}
		}
		return false;
	} 

    private void validateAuthority(HttpServletRequest httpRequest) {
    	//白名单处理
		
	}

	@Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
  
}  