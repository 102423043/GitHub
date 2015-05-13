package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 訊息通知，負責接收從App agent的訊息，並回傳分析結果
 * */
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

		doGet(request, response);
	}

}
