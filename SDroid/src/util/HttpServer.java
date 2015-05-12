package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HttpServer", urlPatterns = { "/HttpServer.do" })
public class HttpServer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PolicyMatchedUtil pmUtil;
	
	public HttpServer() {
		super();
		pmUtil = new PolicyMatchedUtil();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogInfo log = new LogInfo();

		String pkname = request.getParameter("pkName");
		log.info("pkname: %s", pkname);
		String status = pmUtil.analysisPolicyMatched(pkname);
		
		
		PrintWriter output = response.getWriter();
		output.println(status);
		output.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
