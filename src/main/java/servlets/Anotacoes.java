package servlets;

public class Anotacoes {/*
  
  podemos usar o java script para enviar a requisi��o para a servlete mais com
  o java script vai ter redirecionamento de p�gina, mais s� ocorre se caso cli-
  carmos no bot�o e n�o se atualizar a tela.
  
  com o Jquery podemos enviar os dados para a servlet por parametros sem precisar
  redirecionar a tela, com o jquery n�o tem redirecionamento de tela.
  
  quando usamos o jquery enviamos a resposta usando o response do metodo
  doGet:
  
  para enviarmos uma mensagem ou uma resposta enviamnos pelo response exemplo.
  
  response.getWriter().write('excluido com sucesso'); eu escrevo na resposta
  
  e na tela onde voc� queira que apareca a resosta ou a mensagem voce pode 
  pegar com java script e jogar na tela.
  
  document.getEelementById('msn').textContent = response;
  
    $.ajax({
				  method:"get", //qual o metodo que vai receber o pos ou o get.
				  url: urlAction,// aqui vai a url do action do formulario.
				  data:"id_login=" + idUser + "&acao=deletarAjax",// data s�o passados os dados
				  success: function(response){// caso deu tudo certo usa o success com a resposta.
					  
					  limparForm();
					  document.getElementById('msg').textContent = response;
				  }
				  

     document.getElemetById('')-> pega o elemento pelo id.
     .textContent -> pega o conteudo do texto.
     = response - > atribui a mensagem que veio do response

    biblioteca:
    uma bastante usada � o jackson java ou jackson java maven, � para retorna
    valores em json.
    
    fun��o jquery $()
    onde tem este sinal > quer dizer dentro # � o id da tabela,
    .remove(); para remover as linhas.
    $('#tabelaresultados > tbody > tr').remove(); para remover 
    
    para adicionar valores usando a fun��o jquery:
    $('#tabela > tbody').append('<tr><td>'++'</td></tr>')

*/
}
