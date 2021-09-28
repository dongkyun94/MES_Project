package line.command;

import java.net.http.HttpClient.Redirect;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import line.service.LineInsertRequest;
import line.service.LineInsertService;
import member.command.CommandHandler;

public class LineInsertHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newLineForm.jsp";
	private LineInsertService lineInsertService = new LineInsertService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		//User user = (User)req.getSession(false).getAttribute("authUser");
		LineInsertRequest lineInsertReq = creatLineInsertRequest(req);
		lineInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		String newLineNo = lineInsertService.insert(lineInsertReq);
		req.setAttribute("newLineNo", newLineNo);
		return "linelist.do";
	
	}

	private LineInsertRequest creatLineInsertRequest(HttpServletRequest req) {
		return new LineInsertRequest(
				Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")), 
				req.getParameter("line_cd"), req.getParameter("line_nm"), req.getParameter("use_yn"),
				req.getParameter("in_date"));
	}


	
}