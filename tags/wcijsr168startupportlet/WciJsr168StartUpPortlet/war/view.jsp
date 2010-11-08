<%@include  file="header.jsp"%>

<portlet:defineObjects />

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<p align="center"><font size="+1" color="navy">
	<%=request.getContextPath() %>
    <b><%=renderRequest.getPortletSession().getAttribute("title")%></b></font>
</p>

Here now start the GWT...<br>

<% 
	String path = request.getContextPath();
	String _basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	
	out.println("Current user account: " + request.getRemoteUser());
%>	

	<!-- Don't forget to write about the Apache configuration you need for this	 -->
	<script type="text/javascript" language="javascript" src="/wcijsr168startupportlet/wcijsr168startupportlet.nocache.js"></script>

    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <h1>Web Application Starter Project</h1>

    <table align="center">
      <tr>
        <td colspan="2" style="font-weight:bold;">Please enter your name:</td>        
      </tr>
      <tr>
        <td id="nameFieldContainer"></td>
        <td id="sendButtonContainer"></td>
      </tr>
      <tr>
        <td colspan="2" style="color:red;" id="errorLabelContainer"></td>
      </tr>
    </table>


