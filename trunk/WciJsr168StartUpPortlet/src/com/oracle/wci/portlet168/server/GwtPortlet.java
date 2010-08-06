package com.oracle.wci.portlet168.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

/**
 * Implements JSR168 standard.
 * 
 * @author L.Pelov
 */
public class GwtPortlet extends GenericPortlet {

	private static final long serialVersionUID = 1273116347876976246L;

	private String viewUrl;
	private String editUrl;
	private String helpUrl;
	private String gwtUrl;

	/**
	 * Extend the current PortletMode with one more, which I call 'gwt'.
	 * Experimental!
	 * 
	 * @author L.Pelov
	 */
	public static class PortletModeGwt extends PortletMode {
		public static final PortletMode GWT = new PortletMode("gwt");

		public PortletModeGwt(String arg0) {
			super(arg0);
		}
	}

	/**
	 * Executed one time, upon portlet initialisation.
	 * 
	 * @param config
	 */
	public void init(PortletConfig config) throws PortletException {

		super.init(config);

		// init method of a portlet is a good place to perform
		// any necessary, 1x configurations.
		viewUrl = config.getInitParameter("view_url");
		editUrl = config.getInitParameter("edit_url");
		helpUrl = config.getInitParameter("help_url");
		gwtUrl = config.getInitParameter("gwt_url");
	}

	/**
	 * Add own dispather method, which could be used with you wish to dispatch
	 * to own gwt view
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		PortletMode mode = request.getPortletMode();

		if (mode.equals(PortletModeGwt.GWT)) {
			doGwt(request, response);
		}
		else {
			super.doDispatch(request, response);
		}
	}

	/**
	 * Executed in response to action on portlet, like submitting a form.
	 * 
	 * @param request
	 * @param response
	 */
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {

		System.out.println("processAction");
		
		if (request.getPortletMode().equals(PortletMode.EDIT)) {
			String errorMessage = null;
			boolean isValid = false;

			// get portlet preferences interface
			PortletSession session = request.getPortletSession();
			session.setAttribute("pref1", null);
			// session.setAttribute("pref2", null);
			// session.setAttribute("pref3", null);

			// Get our edit form's data...
			PortletPreferences preferences = request.getPreferences();
			// String formAgr1 = request.getParameter("form_agr1");
			// String formArg2 = request.getParameter("form_agr2");

			// # set the new properties to prepare them to store
			// preferences.setValue("pref1", formAgr1);
			// preferences.setValue("pref2", formAgr1);

			try {
				// This will fire our PreferencesValidation validate method..
				preferences.store();
				isValid = true;
			}
			catch (ValidatorException ve) {
				// Assign values from ActionResponse to RenderRequest for
				// redisplay in form.
				response.setRenderParameter("from_arg1", request
						.getParameter("from_arg1"));
				response.setRenderParameter("from_arg2", request
						.getParameter("from_arg2"));

				errorMessage = "An error occurred..." + ve.getMessage();
				response.setRenderParameter("errorMessage", errorMessage);
			}

			// in our case this could be GWT view
			if (isValid) {
				response.setPortletMode(PortletMode.VIEW);
			}
		}
	}

	/**
	 * Called by render method to prepare markup for user display.
	 * 
	 * @param request
	 * @param response
	 */
	public void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		// PortletPreferences preferences = request.getPreferences();

		// String pref1 = preferences.getValue("pref1", "*****");
		// String pref2 = preferences.getValue("pref2", "*****");

		// get the portlet session, which actually extend from the HttpSession
		PortletSession session = request.getPortletSession();

		// set the current user so we can get it from the HttpSession inside the
		// GWT RPC Sevlet
		session.setAttribute("username", request.getRemoteUser(),
				PortletSession.APPLICATION_SCOPE);

		// dispatch to the portlet view
		PortletRequestDispatcher dispatcher = getPortletContext()
				.getRequestDispatcher(viewUrl);
		dispatcher.include(request, response);
	}

	/**
	 * Executed when user requests to edit the portlet's settings. Also
	 * redisplayed upon failure to set portlet's settings correctly (to displays
	 * errors, etc.).
	 * 
	 * @param request
	 * @param response
	 */
	public void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		PortletPreferences preferences = request.getPreferences();

		String pref1 = preferences.getValue("pref1", "****");
		String pref2 = preferences.getValue("pref2", "****");

		// If any errors are encountered from previous edit attempt, provide
		// them to request.
		String errorMessage = request.getParameter("errorMessage");
		if (errorMessage != null) {
			request.setAttribute("pref1", request.getParameter("form_parm1"));
			request.setAttribute("pref2", request.getParameter("form_parm2"));
			request.setAttribute("errorMessage", errorMessage);
		}
		else {
			// no errors. Set initial parameter values.
			request.setAttribute("pref1", pref1);
			request.setAttribute("pref2", pref2);
		}

		PortletRequestDispatcher dispatcher = getPortletContext()
				.getRequestDispatcher(editUrl);
		dispatcher.include(request, response);
	}

	/**
	 * Experimental, gives you own gwt view.
	 * 
	 * @param request
	 * @param response
	 * @throws PortletException
	 * @throws IOException
	 */
	protected void doGwt(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.println("<script language='javascript' src='"
				+ request.getContextPath()
				+ "/wcijsr168startupportlet.nocache.js'></script>");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("");
		writer.println("GWT time:");
		writer.println("User:" + request.getRemoteUser());
		writer.println("<div id='uid'></div>");
		writer.close();

		// PORTLET VARIABLE WERDEN ÜBER SESSIONS ÜBERGEBEN!!!!!
		
		// PortletContext context = getPortletContext();
		// String url = request.getContextPath() +
		// "/wcijsr168startupportlet/greet";
		//        
		// //PortletRequestDispatcher prd =
		// context.getRequestDispatcher(IMAGES_JSP);
		// PortletRequestDispatcher prd = context.getRequestDispatcher(url);
		// request.setAttribute(attr1, new Object());
		// request.setAttribute(attr2, new Object());
		// prd.include(request, response);

		PortletRequestDispatcher dispatcher = getPortletContext()
				.getRequestDispatcher(gwtUrl);
		dispatcher.include(request, response);

	}

	/**
	 * Executed when user follows the 'help' link. Typically, describes what
	 * portlet is/does.
	 * 
	 * @param request
	 * @param response
	 */
	public void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		response.setContentType("text/html");
		PortletRequestDispatcher dispatcher = getPortletContext()
				.getRequestDispatcher(helpUrl);
		dispatcher.include(request, response);
	}

	/**
	 * Executed one time, prior to portlet being garbage collected.
	 */
	public void destroy() {
	}

}
