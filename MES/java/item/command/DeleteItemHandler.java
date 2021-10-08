package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.DeleteItemService;
import member.command.CommandHandler;

public class DeleteItemHandler implements CommandHandler{
	
	private DeleteItemService deleteItemService = new DeleteItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int item_cd = Integer.parseInt(req.getParameter("item_cd"));
		deleteItemService.delete(item_cd);
		return "itemlist.do";
	}

}
