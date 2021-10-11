package equipment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import equipment.service.DeleteEqMasterService;
import member.command.CommandHandler;

public class DeleteEqMasterHandler implements CommandHandler{

	private DeleteEqMasterService deleteEqMasterService = new DeleteEqMasterService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String equip_cd = req.getParameter("equip_cd");
		deleteEqMasterService.delete(equip_cd);
		return "EqMasterlist.do";
	}

}
