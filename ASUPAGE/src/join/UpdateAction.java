package join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import dao.AdminDao;

public class UpdateAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AdminDao dao = AdminDao.getInstance();
		HttpSession session = request.getSession();
		UpdateActionForm UpdateActionForm = (UpdateActionForm)form;
		
		String email = (String)session.getAttribute("sessionID");
		if(email == null || email.isEmpty())
		{
			request.getSession().invalidate();
			return mapping.findForward("main");
		}
		
		
		
		if(UpdateActionForm instanceof UpdateActionForm) {
			ActionMessages msg = UpdateActionForm.validate(mapping, request);
			if(msg != null && !msg.isEmpty()) {
				request.setAttribute("info", dao.getUserInfo(email));
				saveMessages(request, msg);
				return mapping.getInputForward();
			}
		}
		
		dao.doUpdate(email,UpdateActionForm);
		session.setAttribute("sessionID", UpdateActionForm.getEmail());
		return mapping.findForward("success"); 
	}

}
