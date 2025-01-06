//	HTTPServlet을 상속 받는다

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//	/hs URL에 연결
public class HelloServlet extends HttpServlet {
	private static final Logger logger =
			Logger.getLogger("HelloServlet");
	private String appName = null;
	private String dbUser = null;
	private String dbPass = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 서블릿 초기화 코드
		super.init(config);
		
		logger.info("[LifeCycle]: init");
		//	Context Parameter 읽어오기
		ServletContext context = getServletContext();
		
		appName = context.getInitParameter("appName");
		dbUser = context.getInitParameter("dbUser");
		dbPass = context.getInitParameter("dbPass");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// method 관계없이 요청과 응답 처리
		logger.info("[LifeCycle]: sevice");
		//	요청 메서드
		logger.info("Method:" + req.getMethod());
		
		//	분기는 개발자 몫
		if (req.getMethod().equals("GET")) {
			doGet(req, resp);
		} else if (req.getMethod().equals("POST")) {
			doPost(req, resp);
		} else {
			super.service(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("[LifeCycle]: doGet");
		//	응답 포맷 결정
		//	text/html -> MIME Type
		resp.setContentType("text/html; charset=UTF-8");
		
		//	name 파라미터를 받음
		String name = req.getParameter("name");
		
		if (name == null) {
			name = "Anonymous";
		}
		
		//	환영 메시지 출력
//		super.doGet(req, resp);
		
		//	서블릿 파라미터 받아오기
		ServletConfig config = getServletConfig();
		logger.info("ServletName:" + config.getInitParameter("servletName"));
		logger.info("describtion:" + config.getInitParameter("description"));
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>App Name: " + appName + "</h1>");
		out.println("<h1>Hello Servlet</h1>");
		out.printf("<p>Welcome, %s님</p>", name);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//	ContentType 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		logger.info("[LifeCycle]: doPost");
		
		// POST 메서드 요청 처리
		//	클라이언트로부터 전송된 모든 데이터를 루프 돌면서 출력
		PrintWriter out = resp.getWriter();
		out.println("<h1>폼 데이터 처리</h1>");
		
		out.println("<p>폼으로부터 전송된 데이터</p>");
		Enumeration<String> params = req.getParameterNames();
		
		out.println("<ul>");
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();	//	파라미터이름
			String paramValue = req.getParameter(paramName);
			
			out.printf("<li>%s=%s<li>", paramName, paramValue);
		}
//		super.doPost(req, resp);
		out.println("</ul>");
	}
	
	@Override
	public void destroy() {
		logger.info("[LifeCycle]: Servlet Shutdown...");
		super.destroy();
	}
}
