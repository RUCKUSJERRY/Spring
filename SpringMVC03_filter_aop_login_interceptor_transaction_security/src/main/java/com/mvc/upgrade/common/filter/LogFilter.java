package com.mvc.upgrade.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	// 필터가 시작될때
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String remoteAddr = req.getRemoteAddr();
		// IP Address
		String uri = req.getRequestURI();
		// URL 중 일부를 반환한다. 프로토콜 이름에서 쿼리 문자열까지의 URL을 포함하는 문자열? (contextPath) /upgrade/
		String url = req.getRequestURL().toString();
		// 클라이언트가 요청을 만드는데 사용하는 URL을 재구성, 스트링버퍼 객체, 그래서 toString쓰는듯?? http://localhost:8787/upgrade/
		// 프로토콜 + 서버네임 + 포트넘버 + 서버패스
		String queryString = req.getQueryString();
		// URL 경로 뒤에 포함하는 쿼리스트링을 리턴한다. key=value 형태 command=list.do
		
		String referer = req.getHeader("referer");
		// getHeader : 지정한 요청의 헤더 값을 문자열로 반환한다.
		// referer : 이전 페이지의 url 
		String agent = req.getHeader("User-Agent");
		// agent : 사용자 정보 (브라우저 버전, OS 같은거)
		
		StringBuffer sb = new StringBuffer();
		sb.append("\n* remoteAddr : " + remoteAddr)
		  .append("\n* uri : " + uri)
		  .append("\n* url : " + url)
		  .append("\n* queryString : " + queryString)
		  .append("\n* referer : " + referer)
		  .append("\n* agent : " + agent)
		  .append("\n");
		
		logger.info("\nLOG Filter" + sb);
		
		chain.doFilter(req, response);
		

	}

	
	
	@Override
	// 필터가 종료될때
	public void destroy() {

	}

}
