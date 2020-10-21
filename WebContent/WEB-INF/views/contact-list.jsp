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
<table>
	<thead>
        <tr>
        	<th class="col s12"> Id </th>
            <th class="col s12"> Prénom </th>
            <th class="col s12"> Nom </th>
            <th class="col s12"> Email </th>
        </tr>
    </thead>
    <tbody>
    <%for (Contact c: (java.util.ArrayList<Contact>) request.getAttribute("contacts")){%>
        <tr>
        	<td><%= c.getId() %></td>
            <td><form class="col s12" method="post" action="/CarnetContactStart/findContact">
            <input type="hidden" name="Id" value="<%= c.getId() %>"/>
            <a onclick="parentNode.submit();"><%= c.getFirstName() %></a></form></td>
            <td><%= c.getLastName() %></td>
            <td><%= c.getEmail() %></td>
        </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>