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

public class LoginNewAction extends Action {
	ActionForward forward;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, NamingException {
		if (request.getMethod().equals("GET")) {
			forward = doGet(mapping, _form, request, response);
		} else if (request.getMethod().equals("POST")) {
			if (_form instanceof ProfileForm) {
				ProfileForm form = (ProfileForm) _form;
				ActionErrors errors = form.validate(mapping, request);
				if (errors != null && !errors.isEmpty()) {
					saveErrors(request, errors);
					return mapping.getInputForward();
				}
				forward = doPost(mapping, _form, request, response);
			}
		}
		return forward;
	}

	public ActionForward doGet(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("ok");

	}

	public ActionForward doPost(ActionMapping mapping, ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) {
		LoginDao dao = new LoginDao();// 実際処理する為のクラス//
		ProfileForm form = (ProfileForm) _form;
		if (form.getSelect() == 1) {
			request.setAttribute("form", form);
			return mapping.findForward("1");
		}
		dao.doInsert(form);
		request.setAttribute("form", form);
		dao.OneList(form.getEmail(), form.getPassWord());
		HttpSession session = request.getSession(true);
		session.setAttribute("admin", dao.getAdmin());
		return mapping.findForward("new");
	}
}