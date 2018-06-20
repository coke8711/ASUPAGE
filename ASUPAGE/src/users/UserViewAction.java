package users;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.UserDao;

public class UserViewAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {

		UserViewForm form = (UserViewForm) _form;
		UserDao dao = new UserDao();
		request.setAttribute("beans", dao.getUserAll(form));
		request.setAttribute("pullDownListC1", dao.doPullDownClassification1());
		request.setAttribute("pullDownListC2", dao.doPullDownClassification2());
		return mapping.findForward("ok");

	}

}
