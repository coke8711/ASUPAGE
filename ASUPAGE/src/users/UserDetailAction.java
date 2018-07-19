package users;

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

public class UserDetailAction extends Action {
	@SuppressWarnings("null")
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession(false);// セッションを持っているか確認する
		if (session == null && session.getAttribute("admin") == null) {
			// セッションが発行されている
			return mapping.findForward("Login");
		}
		UserDetailForm form = (UserDetailForm) _form;
		UserDao dao = new UserDao();
		request.setAttribute("bean", dao.getOneRecode(form.getUserId()));
		return mapping.findForward("ok");

	}
}
