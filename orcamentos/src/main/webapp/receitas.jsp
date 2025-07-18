<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("listaReceitas");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Controle de Orçamento</title>
<link rel="icon" href="img/bolsa-de-dinheiro.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1 class="botaor1">Lista de receitas</h1>
	<div class="botoes">
		<a href="novoReceita.html">
			<button class="botao1">Adicionar receita</button>
		</a>
		<a href="main">
			<button class="azul">Orçamentos</button>
		</a>
	</div>
	<p class="botaor1">Total de receita: R$ <%= request.getAttribute("totalReceitas") %></p>
	<table class="tabela-receita" id="tabela">
		<thead>
			<tr>
				<th>Categoria</th>
				<th>Valor</th>
				<th>Descrição</th>
				<th>Data</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getCategoria()%></td>
				<td>R$ <%=lista.get(i).getValor()%></td>
				<td><%=lista.get(i).getDescricao()%></td>
				<td><%=lista.get(i).getData()%></td>
				<td>
					<div class="botoes">
						<a href="selectReceita?id=<%=lista.get(i).getId()%>" class="azul">Editar</a>
						<a href="javascript: confirmarExclusaoReceita(<%=lista.get(i).getId()%>)" class=vermelho>Excluir</a>
					</div>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>