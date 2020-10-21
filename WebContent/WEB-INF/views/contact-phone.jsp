<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.lip6.entities.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Téléphone</title>
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
         for (i=0;i<number;i++){
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
   
     <% Contact contact = (Contact) request.getAttribute("contact"); %>
     
      <div class="row">
    <div class="col s12">
    <h5 class="header grey-text ">Ajouter des numéros de téléphones</h5>
</div>
</div>
<div class="row">
 <form class="col s12" method="post" action="/CarnetContactStart/addPhone">
  <input type="hidden" name="Id" value="<%=contact.getId() %>"/>
      <div class="row">
        <div class="input-field col s12">
			<label>Nombre de numéro de téléphone: (max. 10)</label>
				<input type="text" id="phone" name="phone" ><br/><br>
				<a href="#" id="filldetails" onclick="addFields()">Créer les champs</a>
		    	<div id="container">
		    	</div>
        	</div>
        </div>
           <div class="row">
        <div class="input-field col s12">
         <button class="btn waves-effect waves-light right" type="submit" name="action">Ajouter
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