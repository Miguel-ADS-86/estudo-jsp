package filters;

public class Anotacoes {/*
 tudo que passar de requisição e respostas passam pelo filtro. Como se fosse a porta
 de entrada do sistema vai passar por ele.
 
 O filter na classe ele te 3 metodos, o metodo construtor, o destroy, doFilter,
 init.
 
 a anotação @WebFilter -> intercepta todas as requisicoes que vierem do projeto
 ou mapeamento
      
 quando passamos parametros na url exemplo:
 	? para passar parametros.
 	para se colocar mais de um parametro usamos o &.
 
 ("/index.jsp?url="+concatena com o valor)
 
 commo funciona a passagem de parametros na url:
 primeiro a pagina depois ? para coloca o nome do parametro e
 depois o valor do parametro
 
 Ex: localhost:8080//index.jsp?nome=Miguel
 
 quando eu quero colocar maid de um parametro com valor, separamos
 os parametros por &.
 
 Ex:localhost:8080//index.jsp?nome=Miguel&idade=36
 
 se caso no valor voce coloca espaco ele substitui o espaco por
 %20.
 Ex:localhost:8080//index.jsp?nome=Miguel Pereira&idade=36
 localhost:8080//index.jsp?nome=Migue%20Pereiral&idade=36
 
*/}
