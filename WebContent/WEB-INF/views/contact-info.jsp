<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.lip6.entities.PhoneNumber"%>
<%@page import="com.lip6.entities.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <%@ include file="./fragments/links.jspf" %>
<title>Information</title>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>

<div id="main-container" class="container z-depth-2">
	   
	<% Contact contact = (Contact) request.getAttribute("contact"); %>
	
	<div class="row">
	  <div class="col s12">
	     <h5 class="header grey-text ">${contact.firstName} ${contact.lastName}</h5>
	  </div>
	</div>
	<div class="row">
       <div class="col s12">
       <label for="email">Email: </label>
       </div>
     </div>
    <div class="row">
       <div class="col s12">
			${contact.email}
       </div>
     </div>
    <div class="row">
       <div class="col s12">
       <label for="address">Adresse: </label>
       </div>
     </div>
      <div class="row">
       <div class="col s12">
       		${contact.address.street}
       </div>
     </div>
     <div class="row">
       <div class="col s12">
			${contact.address.city}
       </div>
     </div>
     <div class="row">
       <div class="col s12">
			${contact.address.zip}
       </div>
     </div>
     <div class="row">
       <div class="col s12">
			${contact.address.country}
       </div>
     </div>
    <div class="row">
       <div class="col s12">
       <label for="num�ro">Num�ro(s) de t�l�phone(s): </label>
       </div>
     </div>
     <%for (PhoneNumber p: (java.util.Set<PhoneNumber>) contact.getPhones()){%>
     <div class="row">
       <div class="input-field col s6">
			<%= p.getPhoneKind() %>
       </div>
       <div class="input-field col s6">
			<%= p.getPhoneNumber() %>
       </div>
     </div>
     <%} %>
     
     <div class="row">
      <div class="row">
        <div class="input-field col s6">
        <form class="col s12" method="post" action="/CarnetContactStart/getPhone">
         <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
         <button class="btn waves-effect waves-red left" type="submit" name="tel">Ajouter numeros
    		<i class="material-icons left">send</i>
  		 </button>
  		</form>
        </div>
        <div class="input-field col s6">
         <form class="col s12" method="post" action="/CarnetContactStart/getPhone2">
         <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
         <button class="btn waves-effect red waves-light right" type="submit" name="maj">Supprimer numeros
    		<i class="material-icons left">close</i>
  		 </button>
  		 </form>
       </div>
      </div>
        
      <div class="row">
        <div class="input-field col s6">
         <form class="col s12" method="post" action="/CarnetContactStart/getContact">
         <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
         <button class="btn waves-effect waves-light left" type="submit" name="maj">Mettre � jour le contact
    		<i class="material-icons left">refresh</i>
  		 </button>
  		</form>
       
        </div>
        <div class="input-field col s6">
         <form class="col s12" method="post" action="/CarnetContactStart/deleteContact">
         <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
         <button class="btn waves-effect red waves-red right" type="submit" name="suppr">Supprimer le contact
    		<i class="material-icons left">close</i>
  		 </button>
  		</form>
        </div>
      </div>
      
</div>
</div>
      .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>