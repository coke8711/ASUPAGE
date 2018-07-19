package login;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.LoginDao;

public class ProfileAction extends Action {
	ActionForward forward;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, NamingException {
		if (request.getMethod().equals("GET")) {
			forward = doGet(mapping, _form, request, response);
		} else if (request.getMethod().equals("POST")) {
			forward = doPost(mapping, _form, request, response);
		}
		return forward;
	}

	public ActionForward doGet(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);// セッションを持っているか確認する
		if (session.getAttribute("admin") == null) {
			// セッションが発行されていない
			return mapping.findForward("login");
		}
		return mapping.findForward("profile");

	}

	public ActionForward doPost(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);// セッションを持っているか確認する
		if (session.getAttribute("admin") == null) {
			// セッションが発行されていない
			return mapping.findForward("login");
		}
		LoginDao dao = new LoginDao();// 実際処理する為のクラス//
		ProfileForm form = (ProfileForm) _form;
		int i = form.getSelect();
		switch (i) {

		case 1:
			request.setAttribute("form", form);
			return mapping.findForward("update");
		case 2:
			request.setAttribute("form", form);
			ActionErrors errors = form.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.findForward("update");
		}
			return mapping.findForward("updateC");
		case 3:
			session.setAttribute("admin", form);
			dao.doUpdate(form);
			return mapping.findForward("profile");
		case 4:
			request.setAttribute("form", form);
			return mapping.findForward("delete");
		case 5:
			request.setAttribute("form", form);
			dao.doDelete(form.getEmail());
			return mapping.findForward("logout");
		}
		return mapping.findForward("profile");
	}
}