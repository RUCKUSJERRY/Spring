package com.mvc.upgrade.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[Interceptor] : preHandle");
		
		// spring 3.2 이상부터는 servlet-context.xml에서 <exclude-mapping-path> 를 사용하여 설정 가능
		if(request.getRequestURI().contains("/loginform.do")
			|| request.getRequestURI().contains("/ajaxlogin.do")
			|| request.getRequestURI().contains("/test.do")
			|| request.getRequestURI().contains("/registform.do")
			|| request.getRequestURI().contains("/registres.do")) {
			return true;
			
		}
			
		if (request.getSession().getAttribute("login") != null) {
			return true;
		}
		
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect("loginform.do");
			return false;
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView != null) {
			logger.info(modelAndView.getViewName());
		}
		
		logger.info("[Interceptor] : postHandel");
		
		
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("[Interceptor] : afterCompletion");

	}

}
