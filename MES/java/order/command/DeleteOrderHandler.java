package order.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import order.service.DeleteOrderService;

public class DeleteOrderHandler implements CommandHandler{
	
	private DeleteOrderService deleteOrderService = new DeleteOrderService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order_no = req.getParameter("no");
		deleteOrderService.delete(order_no);
		return "orderlist.do";
	}

}
