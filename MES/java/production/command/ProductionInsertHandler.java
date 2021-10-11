package production.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import production.service.ProductionInsertRequest;
import production.service.ProductionInsertService;

public class ProductionInsertHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/productionlist.do";
	private ProductionInsertService productionInsertService = new ProductionInsertService();
	
	
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
		
		ProductionInsertRequest productionInsertReq = createProductionInsertRequset(req);
		productionInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		String newWoNo = productionInsertService.insert(productionInsertReq);
		req.setAttribute("newWoNo", newWoNo);
		
		return "productionlist.do";
	}
	
	private ProductionInsertRequest createProductionInsertRequset(HttpServletRequest req) {
		return new ProductionInsertRequest(Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")), 
				req.getParameter("order_no"), req.getParameter("wo_no"), req.getParameter("line_cd"), req.getParameter("equip_cd"), 
				req.getParameter("start_dt"), req.getParameter("start_shift"), req.getParameter("end_dt"), req.getParameter("end_shift"), 
				req.getParameter("flag_end"), Integer.parseInt(req.getParameter("plan_qty")), req.getParameter("remark"));
	}

	
	

}
