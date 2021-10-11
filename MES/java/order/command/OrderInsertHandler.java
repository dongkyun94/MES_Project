package order.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import order.service.OrderInsertRequest;
import order.service.OrderInsertService;

public class OrderInsertHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newOrderForm.jsp";
	private OrderInsertService orderInsertService = new OrderInsertService();
	
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
		OrderInsertRequest orderInsertReq = creatOrderInsertRequest(req);
		orderInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		String newOrderNo = orderInsertService.insert(orderInsertReq);
		req.setAttribute("newOrderNo", newOrderNo);
		return "orderlist.do";
	}

	private OrderInsertRequest creatOrderInsertRequest(HttpServletRequest req) {
		return new OrderInsertRequest(
				Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")), 
				null, req.getParameter("order_dt"), Integer.parseInt(req.getParameter("item_cd")), req.getParameter("delivery_dt"), 
				Integer.parseInt(req.getParameter("order_qyt")), req.getParameter("order_status"), req.getParameter("remark"));
	}
	
	
}
