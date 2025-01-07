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
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns="/*")
public class LoggingFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoggingFilter.class.getSimpleName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[LoggingFilter]: init");
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//	요청 URI 정보를 로깅하는 로직
		HttpServletRequest httpRequest = (HttpServletRequest)req;	//	요청 정보를 HttpServletRequest로 캐스팅
		logger.info("[LoggingFilter]: doFilter");
		logger.info("요청 URI: " + httpRequest.getRequestURI());	//	Filter 통과시켜서 로깅 기록 남김

		PrintWriter out = resp.getWriter();
		out.println("<h6>LoggingFilter 작동 전</h6>");
		
		//	요청을 다음 필터나 서블릿으로 전달
		chain.doFilter(req, resp);
		
		out.println("<h6>LoggingFilter 작동 후</h6>");
		
		//	응답이 돌아온 후, 응답 상태 로깅
		logger.info("response status: " + resp.getContentType());
	}

	@Override
	public void destroy() {
		logger.info("[LoggingFilter]: destroy");
		Filter.super.destroy();
	}
	
}
