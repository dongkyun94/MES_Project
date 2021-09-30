package factory.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.service.FactoryInsertRequest;
import factory.service.FactoryInsertService;
import member.command.CommandHandler;

public class FactoryInsertHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/newFactoryForm.jsp";
	private FactoryInsertService factoryInsertService = new FactoryInsertService();
	
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
		FactoryInsertRequest factoryInsertReq = creatFactoryInsertRequest(req);
		factoryInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newFactoryNo = factoryInsertService.insert(factoryInsertReq);
		req.setAttribute("newFactoryNo", newFactoryNo);
		return "factorylist.do";
	}

	private FactoryInsertRequest creatFactoryInsertRequest(HttpServletRequest req) {
		return new FactoryInsertRequest(
				Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")),
				req.getParameter("plant_nm"),req.getParameter("valid_fr_dt"),req.getParameter("valid_to_dt"),req.getParameter("remark"),req.getParameter("in_date") 
				);
	}

}
