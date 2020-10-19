<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Accueil</title>
  <%@ include file="./fragments/links.jspf" %>
</head>
<body class="index-page">
<%@ include file="./fragments/navbar.jspf" %>

  <div class="section " id="index-banner">
    <div class="container">
   
      <h1 class="header center  accent-3">Bienvenue</h1>
      <div class="row center">
        <h5 class="header col s12 light text-bold "><strong>Recherchez et modifiez vos contacts au sein de notre application.</strong></h5>
      </div>
      <div class="row center">
        <a href="/CarnetContactStart/contact-create" id="download-button" class="btn-large waves-effect blue darken-3  waves-light">C'est parti !</a>
      </div>
      <br><br>

    </div>
  </div>
  

    
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

