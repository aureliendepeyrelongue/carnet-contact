<%@page import="java.util.List"%>
<%@page import="com.lip6.entities.Contact"%>
<%@page import="com.lip6.services.ContactService"%>
<%@page import="java.util.Set"%>
<%@page import="com.lip6.entities.ContactGroup"%>
<%@page import="com.lip6.util.JSPUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Accueil</title>
  <%@ include file="./fragments/links.jspf" %>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>

 <div id="main-container" class="container z-depth-2">
   
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Mettre un jour un groupe</h5>
</div>
</div>
<% ContactGroup cg = (ContactGroup) request.getAttribute("contactGroup"); 

if(cg != null){

%>
      <div class="row">
    <form class="col s12" method="POST">
      <div class="row">
        <div class="input-field col s12">
          <input placeholder="Placeholder" value="<%=cg.getGroupName()%>" id="first_name" name="groupName" type="text" class="validate">
          <label for="first_name">Nom du groupe</label>
        </div>
      </div>
            <div class="row">
       <div class="input-field col s12">
    <select multiple name="contacts">
      <option value="" disabled selected>Choisissez un adhérent</option>
      <%
      List<Contact> cList = (List)request.getAttribute("contactList");
      Set<Contact> adherents = cg.getContacts();
        		  
        for(Contact c : cList){
      %>
      <option value="<%=c.getId()%>" <%= JSPUtil.contactIsInSet(c, adherents) ? "selected" : "" %>><%= c.getFirstName()%> <%=c.getLastName()%> </option>
      <% }%>

    </select>
    <label>Liste des adhérents du groupe</label>
  </div>
  </div>

  <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light right" type="submit" name="action">Mettre à jour
    <i class="material-icons right">send</i>
  </button>
        
        
        </div>
      </div>
    </form>
    </div>
    
    <%	
}else { %>

<p>Erreur groupe introuvable vérifiez l'URL.</p>
<%} %>
  </div>

  

    
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
   <script>$(document).ready(function(){
	    $('select').formSelect();
   });
         </script>
</body>
</html>

