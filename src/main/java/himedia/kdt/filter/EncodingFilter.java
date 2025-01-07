package himedia.kdt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

//	필터는 인터페이스
public class EncodingFilter implements Filter {
	private static Logger logger = Logger.getLogger(EncodingFilter.class.getSimpleName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[EncodingFilter]: init");
		Filter.super.init(filterConfig);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		logger.info("[EncodingFilter]: doFilter");
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<h6>Encoding 필터 작동 전</h6>");
		
		//	뒤쪽 필터로 요청 전달(chain의 역할)
		chain.doFilter(req, resp);
		
		out.println("<h6>Encoding 필터 작동 후</h6>");
	}
	
	@Override
	public void destroy() {
		logger.info("[EncodingFilter] destroy");
		Filter.super.destroy();
	}
}
