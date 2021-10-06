package factory.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import factory.service.DeleteFactoryService;

public class DeleteFactoryHandler implements CommandHandler{
	
	private DeleteFactoryService deleteFactoryService = new DeleteFactoryService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int plant_cd = Integer.parseInt(req.getParameter("plant_cd")) ;
		deleteFactoryService.delete(plant_cd);
		return "factorylist.do";
	}

}
