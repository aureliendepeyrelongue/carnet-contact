<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.lip6.entities.PhoneNumber"%>
<%@page import="com.lip6.entities.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <%@ include file="./fragments/links.jspf" %>
<title>Mise à jour</title>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>

 <div id="main-container" class="container z-depth-2">
   
     <% Contact contact = (Contact) request.getAttribute("contact"); %>
     <% int i = 0; %>
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Créer un contact</h5>
</div>
</div>

      <div class="row">
    <form class="col s12" method="post" action="/CarnetContactStart/updateContact">
     <input type="hidden" name="Id" value="${contact.id}"/>
      <div class="row">
        <div class="input-field col s6">
          <input name="firstName" value="${contact.firstName}" type="text" class="validate"/>
        </div>
        <div class="input-field col s6">
          <input name="lastName" value="${contact.lastName}" type="text" class="validate">
        </div>
      </div>
    
   
      <div class="row">
        <div class="input-field col s12">
          <input name="email" value="${contact.email}" type="email" class="validate">
        </div>
      </div>
       <div class="row">
        <div class="input-field col s12">
          <input name="street" value="${contact.address.street}" type="text" class="validate">
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input name="city" value="${contact.address.city}" type="text" class="validate">
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input name="zip" value="${contact.address.zip}" type="text" class="validate">
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input name="country" value="${contact.address.country}" type="text" class="validate">
        </div>
      </div>
     <%for (PhoneNumber p: (java.util.Set<PhoneNumber>) contact.getPhones()){
     	i = i + 1;
     %>
     <div class="row">
       <div class="input-field col s6">
       <input name="phoneKind<%=i%>" value="<%= p.getPhoneKind() %>" type="text" class="validate"/>
       </div>
       <div class="input-field col s6">
       <input name="phoneNumber<%=i%>" value="<%= p.getPhoneNumber() %>" type="text" class="validate">
       </div>
     </div>
     <%} %>
     <input type="hidden" name="phone" value="<%= i%>"/>
           <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light right" type="submit" name="action">Mettre à jour
    <i class="material-icons right">send</i>
  </button>
        
        
        </div>
      </div>
    </form>
    </div>
    </div>
</body>
</html>