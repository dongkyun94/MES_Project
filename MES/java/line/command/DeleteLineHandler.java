package line.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import line.service.DeleteLineService;

public class DeleteLineHandler implements CommandHandler{
	
	private DeleteLineService deleteLineService = new DeleteLineService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String line_cd = req.getParameter("line_cd");
		deleteLineService.delete(line_cd);
		return "linelist.do";
	}

}

