<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>Tela de login</title>
<style type="text/css">
form{
     position: absolute;
     top:40%;
     left:33%;   
}
h5{
	position: absolute;
    top:30%;
    left:33%;
    right:33%; 
}
#btn_larg{
	width:100%;
}
.msg{
	position: absolute;
	
	font-size:15px;
    top:80%;
    left:33%;
    right:33%;
    color: #41464b;
}
body{
	background-color:;
}
</style>
</head>
<body>
    <h5>Bem vindo ao Sistema</h5> 
               
	<form class="row g-3 needs-validation" action="<%= request.getContextPath() %>/ServletLogin" method="post" novalidate>
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	
		<div class="mb-3">
			<label  class="form-label">Login:</label>
			<input class="form-control" type="text" name="login" required>
		</div>
		<div class="md-3">
			<label class="form-label">Senha:</label>
			<input class="form-control" type="password" name="senha" required id="senha" />
			<label class="form-label">Exibir:</label> <input type="checkbox" onclick="exibirSenha()" />
		</div>
		<div>
	    	<input id="btn_larg" type="submit" value="Acessar" class="btn btn-primary">		
		</div>
	</form>
	<br><br><br><br><br><br><br><br>
	<h5 class="msg">${msg}</h5> <% //para se colocar atributos nas tags html usa-se ${aqui dentro atributo} %>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

    <script type="text/javascript">
    //função para exibir a senha
    function exibirSenha(){
    	let senha = document.getElementById('senha');
    		if(senha.type == 'password'){
    			senha.type = 'text';
    		}else{
    			senha.type = 'password';
    		}
    }
    
 // Example starter JavaScript for disabling form submissions if there are invalid fields
    (() => {
      'use strict'

      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      const forms = document.querySelectorAll('.needs-validation')

      // Loop over them and prevent submission
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }

          form.classList.add('was-validated')
        }, false)
      })
    })()
    </script>
</body>
</html>