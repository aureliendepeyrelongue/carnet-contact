<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Accueil</title>
  <%@ include file="./fragments/links.jspf" %>
  <script type='text/javascript'>
	 function addFields(){
		 //Id phone du formulaire
         var number = document.getElementById("phone").value;
		 // a placer dans le div container
         var container = document.getElementById("container");
		 // si on changer la valeur du nombre de numéros
         while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
         }
         for (i=0;i<number && i < 10 ;i++){
             container.appendChild(document.createTextNode("Type de numéro  " + (i+1)));
             var input = document.createElement("input");
             input.type = "text";
             input.name = "phonekind" + i;
             container.appendChild(input);
             container.appendChild(document.createTextNode("Numéro " + (i+1)));
             var input = document.createElement("input");
             input.type = "text";
             input.name = "phonenumber" + i;
             container.appendChild(input);
             container.appendChild(document.createElement("br"));
         }
     }
   </script>
</head>
<body>
<%@ include file="./fragments/navbar.jspf" %>

 <div id="main-container" class="container z-depth-2">
   
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Créer un contact</h5>
</div>
</div>

      <div class="row">
    <form class="col s12" method="post" action="/CarnetContactStart/addContact">
      <div class="row">
        <div class="input-field col s6">
          <input id="firstName" placeholder="Placeholder" name="firstName" type="text" class="validate"/>
          <label for="firstName">Prénom</label>
        </div>
        <div class="input-field col s6">
          <input id="lastName" name="lastName" type="text" class="validate">
          <label for="lastName">Nom de famille</label>
        </div>
      </div>
    
   
      <div class="row">
        <div class="input-field col s12">
          <input  id="email" name="email" type="email" class="validate">
          <label for="email">Email</label>
        </div>
      </div>
       <div class="row">
        <div class="input-field col s12">
          <input id="street" name="street" type="text" class="validate">
          <label for="street">Rue</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="city" name="city" type="text" class="validate">
          <label for="city">Ville</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="zip" name="zip" type="text" class="validate">
          <label for="zip">Code Postal</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <input id="country" name="country" type="text" class="validate">
          <label for="country">Pays</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
			<label for="phone" >Nombre de numéro de téléphone: (max. 10)</label>
				<input type="number" max="10" id="phone" name="phone" ><br/><br>
		
        	</div>
        </div>
       
        <div class="row">
        <div class="col s12">
        	<a href="#" id="filldetails" class="btn" onclick="addFields()">Créer les champs</a>
		    	
        </div>
        </div>
        <div class="row">
        <div class="col s12">
        <div id="container">
		    	</div>
        </div>
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

