<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.lip6.entities.Contact"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Liste des contacts</title>
<%@ include file="./fragments/links.jspf" %>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>
 <div id="main-container" class="container">
   <div class="row">
    <div class="col s12">
<table>
	<thead>
        <tr>
        	<th > Id </th>
            <th > Prénom </th>
            <th > Nom </th>
            <th > Email </th>
            <th > Ville </th>
        </tr>
    </thead>
    <tbody>
    <%for (Contact c: (java.util.ArrayList<Contact>) request.getAttribute("contacts")){%>
        <tr>
        	<td><%= c.getId() %></td>
            <td><form class="col s12" method="post" action="/CarnetContactStart/findContact">
            <input type="hidden" name="Id" value="<%= c.getId() %>"/>
            <a onclick="parentNode.submit();" style="cursor:pointer;"><%= c.getFirstName() %></a></form></td>
            <td><%= c.getLastName() %></td>
            <td><%= c.getEmail() %></td>
            <td><%= c.getAddress().getCity() %></td>
        </tr>
    <%}%>
    </tbody>
</table>
</div>
</div>
</div>
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>