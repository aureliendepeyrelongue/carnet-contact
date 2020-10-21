<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="com.lip6.entities.ContactGroup" %>
          <%@ page import="java.util.Set" %>
        <%@ page import="com.lip6.entities.Contact" %>
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
    <h5 class="header grey-text ">Détails du groupe</h5>
</div>
</div>
<%
ContactGroup cg = (ContactGroup)request.getAttribute("contactGroup");

if(cg!= null){

%>
      <div class="row">
     <div class="col s12">
     <h6 >Nom du groupe : <%= cg.getGroupName() %></h6>
     <%
     
     Set<Contact> cSet = cg.getContacts();
     
     if(!cSet.isEmpty())
     {
     
     %>
         <table>
        <thead>
          <tr>
            <th > Id </th>
             <th>Prénom</th>
              <th>Nom</th>
              <th>Email</th>
              <th>Ville</th>
          </tr>
        </thead>

        <tbody>
        <% for(Contact c : cSet){%>
          <tr>
            <td><%= c.getId() %></td>
            <td><form class="col s12" method="post" action="/CarnetContactStart/findContact">
            <input type="hidden" name="Id" value="<%= c.getId() %>"/>
            <a onclick="parentNode.submit();" style="cursor:pointer;"><%= c.getFirstName() %></a></form></td>
            <td><%= c.getLastName() %></td>
            <td><%= c.getEmail() %></td>
            <td><%= c.getAddress().getCity() %></td>
          </tr>
            <% }%>
        </tbody>
      </table>
          <% } else{
          %>
          <p>Pas d'adhérents encore pour ce groupe</p>
          <%}%>
  
          
     </div>
   
 

  <%
}
else{

  %>
<p>Erreur groupe introuvable, vérifiez l'URL</p>
<% 

}
%>

   </div>
   <div class="row">
   <div class="col s12">
              <div style="display:flex;margin-bottom:20px;" class="right">
     <form method="POST" action="/CarnetContactStart/contact-group-delete"><input type="hidden" name="groupId" value="<%=request.getParameter("groupId")%>"><button class="btn red darken-3">Supprimer le groupe</button></form>
      <a style="margin-left:20px;margin-right:10px;" class="btn darken-3" href="/CarnetContactStart/contact-group-update?groupId=<%=cg.getId()%>">Modifier le groupe</a>
     </div>
   </div>
   </div>
     </div>
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

