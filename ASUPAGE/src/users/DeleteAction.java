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

public class DeleteAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		ActionForward forward;
		if (request.getMethod().equals("GET")) {
			forward = doGet(mapping, _form, response, request);
		} else {
			forward = doPost(mapping, _form, response, request);
		}
		return forward;

	}

	@SuppressWarnings("null")
	private ActionForward doGet(ActionMapping mapping, ActionForm _form, HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);// セッションを持っているか確認する
		if (session == null && session.getAttribute("admin") == null) {
			// セッションが発行されている
			return mapping.findForward("Login");
		}
		UserDao dao = new UserDao();
		DeleteForm form = (DeleteForm) _form;
		request.setAttribute("bean", dao.getOneRecode(form.getUserId()));
		return mapping.findForward("ok");
	}

	@SuppressWarnings("null")
	private ActionForward doPost(ActionMapping mapping, ActionForm _form, HttpServletResponse response,
			HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession(false);// セッションを持っているか確認する
		if (session == null && session.getAttribute("admin") == null) {
			// セッションが発行されている
			return mapping.findForward("Login");
		}
		UserDao dao = new UserDao();
		DeleteForm form = (DeleteForm) _form;
		dao.doDelete(form);
		return mapping.findForward("delete");
	}
}
