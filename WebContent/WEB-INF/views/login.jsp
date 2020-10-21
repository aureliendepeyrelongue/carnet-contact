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
    <h5 class="header grey-text ">Se connecter</h5>
</div>
</div>
<% 

String failure = (String)request.getAttribute("badAuthentication");
if(failure!= null && failure.equals("true")){
%>
 <div class="row">
    <div class="col s12 m12">
      <div class="card-panel red">
        <span class="white-text">
       Erreur, email ou nom invalides.
        </span>
      </div>
    </div>
  </div>
<% 
}

%>
      <div class="row">
    <form class="col s12" method="POST">
      <div class="row">
        <div class="input-field col s12">
          <input placeholder="marie.chevrel@gmail.com" id="email" name="email" type="text" class="validate">
          <label for="email">email</label>
        </div>
      </div>
        <div class="row">
        <div class="input-field col s12">
          <input placeholder="Chevrel" id="last_name" name="lastName" type="text" class="validate">
          <label for="last_name">nom</label>
        </div>
      </div>
   
           <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light right" type="submit" name="action">Créer
    <i class="material-icons right">send</i>
  </button>
        
        
        </div>
      </div>
    </form>
    </div>
  </div>

  

    
  
  .<%@ include file="./fragments/footer.jspf" %>
   .<%@ include file="./fragments/scripts.jspf" %>
</body>
</html>

