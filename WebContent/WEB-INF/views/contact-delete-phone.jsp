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
	
	<form class="col s12" method="post" action="/CarnetContactStart/deletePhone">
     <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
     <%for (PhoneNumber p: (java.util.Set<PhoneNumber>) contact.getPhones()){
     	i = i + 1;
     %>
     <div class="row">
       <div class="col s6">
       <label>
       	<input type="checkbox" id="check" name="x<%=i%>" value="<%= p.getId() %>"/>
       	<span></span>
       </label>
       </div>
       <div class="input-field col s6">
			<%= p.getPhoneKind() %>
       </div>
       <div class="input-field col s6">
			<%= p.getPhoneNumber() %>
       </div>
     </div>
     <%} %>
     <input type="hidden" name="phone" value="<%= i%>"/>
	          <button class="btn waves-effect waves-light right" type="submit" name="maj">Supprimer
			<i class="material-icons left">send</i>
	 </button>
	</form>
</div>
</body>
</html>