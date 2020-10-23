<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.lip6.entities.PhoneNumber"%>
<%@page import="com.lip6.entities.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <%@ include file="./fragments/links.jspf" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>

<div id="main-container" class="container z-depth-2">
	   
	<% Contact contact = (Contact) request.getAttribute("contact"); %>
	<% int i = 0; %>
	    <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Supprimer un/des numéros</h5>
</div>
</div>
	
	<div class="row">
	<form class="col s12" method="post" action="/CarnetContactStart/deletePhone">
	
     <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
    
     
     <table>
	<thead>
        <tr>
        	<th > Check </th>
            <th > Type </th>
            <th > Numéro </th>
          
        </tr>
    </thead>
    <tbody>
   <%for (PhoneNumber p: (java.util.Set<PhoneNumber>) contact.getPhones()){
     	i = i + 1;
     %>
        <tr>
        <td> <label>
       	<input type="checkbox" id="check" name="x<%=i%>" value="<%= p.getId() %>"/>
       	<span></span>
       </label></td>
         <td>
			<%= p.getPhoneKind() %>
       </td>
       <td >
			<%= p.getPhoneNumber() %>
       </td>
        </tr>
    <%}%>
    </tbody>
</table>
  
 
     <input type="hidden" name="phone" value="<%= i%>"/>
         <div class="input-field col s12">
	          <button class="btn waves-effect waves-light red right" type="submit" name="maj">Supprimer
			<i class="material-icons left">close</i>
	 </button>
	 </div>
	</form>
	</div>
</div>
  <%@ include file="./fragments/footer.jspf" %>
   <%@ include file="./fragments/scripts.jspf" %>
</body>
</html>