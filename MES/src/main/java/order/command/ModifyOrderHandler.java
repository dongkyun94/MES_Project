package order.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import member.model.Member;
import order.service.ModifyOrderService;
import order.model.Order;
import order.service.ModifyOrderRequest;
import order.service.OrderNotFountException;
import order.service.PermissionDeniedException;

public class ModifyOrderHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/orderlist.jsp";
	
	private ModifyOrderService modifyService = new ModifyOrderService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
		
		try {
			String noVal = req.getParameter("no");
			Order loadData = modifyService.loadData(noVal);
			Member member = (Member) req.getSession().getAttribute("authUser");
			
			if(!canModify(member)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyOrderRequest modReq = new ModifyOrderRequest(noVal, loadData.getOrder_status(), loadData.getDelivery_dt(), loadData.getOrder_qty(), loadData.getRemark());
			req.setAttribute("orderdata", loadData);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Member member = (Member) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("order_no");
		String statusVal = req.getParameter("order_status");
		Date deliveryVal = sdf.parse(req.getParameter("delivery_dt"));
		int qtyVal = Integer.parseInt(req.getParameter("order_qty"));
		String remarkVal = req.getParameter("remark");
		
		ModifyOrderRequest modReq = new ModifyOrderRequest(noVal, statusVal, deliveryVal, qtyVal, remarkVal);
		req.setAttribute("modReq", modReq);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "orderlist.do";
		} catch (OrderNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
