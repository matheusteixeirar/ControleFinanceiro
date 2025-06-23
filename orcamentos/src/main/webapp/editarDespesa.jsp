<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Controle de Orçamento</title>
<link rel="icon" href="img/bolsa-de-dinheiro.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1 class="botaod1">Editar Despesa</h1>
	<form name="form" action="updateDespesa" class="border">
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	<input type="hidden" name="tipo" value="despesa">
		<table class="tabela-despesa2">
			<tr>
				<td><input type="number" min="0" name="valor" class="caixa" value="<% out.print(request.getAttribute("valor")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="categoria" class="caixa" value="<% out.print(request.getAttribute("categoria")); %>"></td>
			</tr>
			<tr>
				<td><textarea name="descricao" rows="3" class="caixa"><%= request.getAttribute("descricao") %></textarea></td>
			</tr>
			<tr>
				<td><input type="date" name="data" class="caixa" value="<% out.print(request.getAttribute("data")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="botao2" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>