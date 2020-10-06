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
 <div class="container z-depth-2">
   
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Créer un groupe de contacts</h5>
</div>
</div>

      <div class="row">
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input placeholder="Placeholder" id="first_name" type="text" class="validate">
          <label for="first_name">Nom du groupe</label>
        </div>
   
      </div>
    
   
           <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light green right" type="submit" name="action">Créer
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

