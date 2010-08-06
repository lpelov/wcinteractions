<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet"%>

<portlet:defineObjects />

<table border="1" align="left">
	<tr>
		<td align="left"><i> <font size="-1">Portlet Mode: <b><%=renderRequest.getPortletMode().toString()%></b></font>
		<br>
		<font size="-1">Window State: <b><%=renderRequest.getWindowState().toString()%></b></font>
		</i></td>
	</tr>
</table>
