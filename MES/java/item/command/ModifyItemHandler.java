package item.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import item.model.Item;
import item.service.ModifyItemRequest;
import item.service.ModifyItemService;
import member.command.CommandHandler;
import order.service.OrderNotFountException;
import order.service.PermissionDeniedException;

public class ModifyItemHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/ItemModify.jsp";
	
	private ModifyItemService modifyService = new ModifyItemService();

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
			int noVal = Integer.parseInt(req.getParameter("no"));
			Item loadData = modifyService.loadData(noVal);
			User user = (User) req.getSession().getAttribute("authUser");
			
			if(!canModify(user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return FORM_VIEW;
			}
			ModifyItemRequest modReq = new ModifyItemRequest(noVal, loadData.getAcct_id(), loadData.getItem_nm(), 
					loadData.getItem_spec(), loadData.getItem_spec2(), loadData.getItem_color(), 
					loadData.getCust_cd(), loadData.getAcct_price(), loadData.getCurrency(), 
					loadData.getUnit_cd(), loadData.getRemark(), user.getId(), new Date());
			req.setAttribute("itemdata", loadData);
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}  catch (OrderNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return FORM_VIEW;
		}
	}
	
	private boolean canModify(User user) {
		return true;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = (User) req.getSession().getAttribute("authUser");
		int item_cd = Integer.parseInt(req.getParameter("item_cd"));
		String acct_id = req.getParameter("acct_id");
		String item_nm = req.getParameter("item_nm");
		String item_spec = req.getParameter("item_spec");
		String item_spec2 = req.getParameter("item_spec2");
		String item_color = req.getParameter("item_color");
		String cust_cd = req.getParameter("cust_cd");
		int acct_price = Integer.parseInt(req.getParameter("acct_price"));
		String currency = req.getParameter("currency");
		String unit_cd = req.getParameter("unit_cd");
		String remark = req.getParameter("remark");
		String up_usr_id = user.getId();
		Date up_date = new Date();
		
		
		ModifyItemRequest modReq = new ModifyItemRequest(item_cd, acct_id, item_nm, item_spec, item_spec2, item_color, cust_cd, acct_price, currency,
				unit_cd, remark, up_usr_id, up_date);
		//req.setAttribute("modReq", modReq);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "itemlist.do";
		} catch (OrderNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
