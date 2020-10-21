<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
        <%@ page import="com.lip6.entities.ContactGroup" %>
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
    <h5 class="header grey-text ">Rechercher un/des groupes de contact</h5>
</div>
</div>
      <div class="row">
        <div class="col s12">
    <form method="POST" >
        <div class="input-field">
          <input id="search" type="search" name="search" required>
          <label class="label-icon" for="search"><i class="material-icons">search</i></label>
          <i class="material-icons">close</i>
        </div>
      </form>
      </div>
    </div>
          <div class="row">
        <div class="col s12">
    <%
     List<ContactGroup> cList = (List<ContactGroup>)request.getAttribute("results");
        		  if(cList!= null && cList.size() != 0){
        			  %>
        			  <ul class="collection">
        			  <% 
        			  for(ContactGroup cg : cList){
        			  %>
      <li class="collection-item avatar">
      <img src="https://iupac.org/wp-content/uploads/2018/05/default-avatar.png" alt="" class="circle">
      <span class="title"><%= cg.getGroupName() %> </span>
      <p>Composé de <%=  cg.getContacts().size() %> adhérents.<br>
      <a href="/CarnetContactStart/contact-group-details?groupId=<%=cg.getId()%>">Plus de détails -></a>
      </p>
      <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
    </li>
  
        			  
        			     
        			  <% 
        			  }
        			  
        			  %>
        			      </ul>
        			  <%  
        		  }
    %>
      </div>
    </div>
  </div>

  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

