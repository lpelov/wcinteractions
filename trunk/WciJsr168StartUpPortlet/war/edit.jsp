<%@include  file="header.jsp"%>
<form method="POST" action="<portlet:actionURL/>">
<table align="center" width="80%" border = "0">
    <thead>  
        <tr>
            <td align="center" colspan="2"><font size="+1" color="navy"><b>Edit Portlet Preferences Form</b></font></td>
        </tr>
    </thead> 
    <tbody>
        <tr>
            <td colspan="2"><hr></td>
        </tr>
        <tr>
	        <td colspan="2" align="center">
			 <%if (renderRequest.getAttribute("errorMessage") != null) { %>
			            <font color="red"><b><%=renderRequest.getAttribute("errorMessage")%></b></font>
			 <%} else { %>
			            <br>
			 <%}%> 
	        </td>
        </tr>
        <tr>
         	<td><b>Portlet Title: </b> </td>
         	<td><input name="<portlet:namespace/>title" type="text" size="30" value="<%=renderRequest.getAttribute("title")%>"></td>
        </tr> 
        <tr>
          <td valign="top"><b>Pref1 value:</b> </td>
          <td><input type="text" name="<portlet:namespace/>pref1" value="<%=renderRequest.getAttribute("sql")%>"/></td>
        </tr>   
        <tr>
			<td align="center" colspan="2">
			<input type="submit">
			<input type="reset">
			</td>
        </tr> 
        <tr>
            <td colspan="2"><hr></td>
        </tr>
    </tbody>
</table>
</form>
