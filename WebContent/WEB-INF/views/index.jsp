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
  ...
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center blue-text">Accueil de Carnet Contact</h1>
      <div class="row center">
        <h5 class="header col s12 light">Recherchez et modifiez vos contacts au sein de notre application.</h5>
      </div>
      <div class="row center">
        <a href="/CarnetContactStart/contact-create" id="download-button" class="btn-large waves-effect waves-light orange">C'est parti !</a>
      </div>
      <br><br>

    </div>
  </div>
  

    
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

