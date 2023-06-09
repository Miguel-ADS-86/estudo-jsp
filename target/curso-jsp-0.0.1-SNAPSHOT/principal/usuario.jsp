<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html lang="pt-br">
<jsp:include page="head.jsp"></jsp:include><!-- aqui eu fa�o o include do head -->

<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbarmainmenu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
									
									    <div class="row">
                                            <div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                <div class="card">
                                                    
                                            <div class="card-block">
                                                 <h4 class="sub-title">Cadastro de Usu�rio</h4>                        
											<form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="userforms">
                                                   
                                                   <input type="hidden" name="acao" id="acao" value="">         
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id_login" id="id_login" class="form-control" readonly="readonly" value="${formUsuario.id_login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Id:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${formUsuario.nome}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" value="${formUsuario.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">E-mail:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" id="login" class="form-control" required="required" autocomplete="off" value="${formUsuario.login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" id="senha" class="form-control" required="required" autocomplete="off" value="${formUsuario.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha:</label>
                                                            </div>
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                            <button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="criarDeleteComAjax()">Excluir</button>
                                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="clearPesquisa()">Pesquisar</button>
                                                                   
                                                        </form>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            <span id="msg">${msg}</span>
                                            
                                         <div class="card" ><!-- inicio card -->
                                        <!-- CARREGA DADOS NO BODY ABAIXO DO FORMULARIO -->
										<div style="height: 300px; overflow: scroll;">
											<!--tabela-->
											<table class="table" id="tabelaresultadosView">
												<thead>
													<tr>
														<th scope="col">C�digo</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody><!-- aqui o codigo de jstl -->
													<c:forEach items="${logins}" var="ml">
														<tr>
															<td><c:out value="${ml.id_login}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out> </td>
															<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=editUser&id=${ml.id_login}">
															 Ver </a>
															 <a class="btn btn-danger" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=deletar&id_login=${ml.id_login}">
															 Excluir </a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									</div><!-- divcard -->
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  
  <!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
    <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usu�rio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- corpo do modal -->                               
         <div class="input-group mb-3">
               <input type="text" class="form-control" id="nomeBusca" placeholder="Nome do Usuario..." aria-label="Recipient's username" aria-describedby="basic-addon2">
               <div class="input-group-append">
               <button class="btn btn-outline-secondary" type="button" onclick="buscarUsuario()">Buscar</button>
               </div>
        </div>
					<div style="height: 300px; overflow: scroll;">
						<!--tabela-->
						<table class="table" id="tabelaresultados">
							<thead>
								<tr>
									<th scope="col">C�digo</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
					<span id="verResultado"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

	<script type="text/javascript">
	  // metodo java script limpar campos
		function limparForm(){
		     //este metodo n funciona com o ajax jeyquery.
		  // document.getElementById('userforms').reset();// tambem limpa os formularios.
		   
			var elementos = document.getElementById("userforms").elements;//ele retorna um array dos elementos html dentro do form.
			for(p=0; p < elementos.length;p++){
				elementos[p].value = "";
			}
		}
		
	  //metodo java script excluir
	  function criarDelete(){
		  if(confirm('Deseja realmente excluir os dados?')){/*confirm() � um metodo do java script*/
		  document.getElementById("userforms").method='get';/*passa o formulario para o metoodo get*/
		  document.getElementById("acao").value = 'deletar';/*atribui��o a acao*/
		  document.getElementById("userforms").submit();// este metodo submit() do java script ele envia o formulario.
		 }
	  }
	  
	  //metodo delete ultilizando ajax com jeyquery
	  // com ajax n�o existe redirecionamento ele n�o faz envio de formulario html
	  // ele envia os dados por parametro e n�o precisa recarregar a tela.
	  
	  function criarDeleteComAjax(){
		  if(confirm("Deseja realmente excluir os dados?")){
			  let urlAction = document.getElementById('userforms').action;// aqui pego o action do formulario, a��o para onde vai.
			  let idUser = document.getElementById('id_login').value;//pegando o valor da caixa de texto id_login
			  //ajaxcom usando o Jquery
			  
			  $.ajax({
				  method:"get", //qual o metodo que vai receber o pos ou o get.
				  url: urlAction,// aqui vai a url do action do formulario.
				  data:"id_login=" + idUser + "&acao=deletarAjax",// data s�o passados os dados
				  success: function(response){// caso deu tudo certo usa o success com a resposta.
					  
					  limparForm();
					  document.getElementById('msg').textContent = response;
				  }
				  
			  }).fail(function(xhr, status,errorthrown){// o faill se caso de erro, o xhr traz os detalhes do erro, status que � o status do erro, errorthrown � a exce��o do erro.
				 
				  alert('erro ao deletar usuario por id' + xhr.responseText);
			  }); 
		  }
	  }
	  
	  function buscarUsuario(){
		 let nome = document.getElementById('nomeBusca').value;
		 if(nome != null && nome != '' && nome.trim() != ''){//validando se tem valor para poder buscar no banco
		  let Usurl = document.getElementById('userforms').action;
           $.ajax({
        	   method:'get',
        	   url:Usurl,
        	   data:'busca='+ nome + '&acao=buscarAjax',
        	   success: function(response){
        		   let valorJson = JSON.parse(response); //converto para o formato json
        	
        		   $('#tabelaresultados > tbody > tr').remove();//remove todas as linhas, pois eu posso ter feito uma pesquisa anterior
        		   
        		   for(let p=0; p < valorJson.length; p++){
        			   // $()func��o jquery e append() adiciona o valor
        			   $('#tabelaresultados > tbody').append('<tr><td>'+valorJson[p].id_login+'</td><td>'+valorJson[p].nome+'</td><td><button class="btn btn-success waves-effect waves-light type="button"" onclick="editUser('+valorJson[p].id_login+')">Ver</button></td></tr>');
        		   }
        		   
        		   document.getElementById('verResultado').textContent = 'Resultado: '+ valorJson.length;
        	   }
           }).fail(function(xhr, status, errorThrown){
        	   alert('Erro ao buscar usu�rio' + xhr.responseText);
           })
		   
		   
		 }
	  }
	  
	  function editUser(id){
		 let Usurl = document.getElementById('userforms').action;
		 //fazer um redirecionamento com java script
		 window.location.href = Usurl+'?acao=editUser&id='+id;
	  }
	  	  
	</script>
</body>

</html>
