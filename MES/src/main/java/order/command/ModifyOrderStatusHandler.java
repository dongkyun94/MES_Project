package order.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import member.model.Member;
import order.service.ModifyOrderStatusService;
import order.service.ModifyStatusRequest;
import order.service.OrderNotFountException;
import order.service.PermissionDeniedException;

public class ModifyOrderStatusHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";
	
	private ModifyOrderStatusService modifyService = new ModifyOrderStatusService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			String noVal = req.getParameter("order_no");
			String statusVal = req.getParameter("order_status");
			Member member = (Member) req.getSession().getAttribute("authUser");
			if(!canModify(member)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyStatusRequest modReq = new ModifyStatusRequest(noVal, statusVal);
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}  catch (OrderNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(Member member) {
		return true;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Member member = (Member) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("order_no");
		String statusVal = req.getParameter("order_status");
		
		ModifyStatusRequest modReq = new ModifyStatusRequest(noVal, statusVal);
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (OrderNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
