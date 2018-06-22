package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.UserDao;
import users.UserViewForm;

public class ViewAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		if(id == null || id.isEmpty())
		{
			request.getSession().invalidate();
			return mapping.findForward("main");
		}
		
		UserViewForm form = (UserViewForm) _form;
		UserDao dao = new UserDao();
		request.setAttribute("beans", dao.getUserAll(form));
		request.setAttribute("pullDownListC1", dao.doPullDownClassification1());
		request.setAttribute("pullDownListC2", dao.doPullDownClassification2());
		return mapping.findForward("ok");

	}

}
