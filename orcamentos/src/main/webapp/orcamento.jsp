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
	<h1>Controle de Orçamento</h1>
	<div class="botoes">
		<a href="receita" class="verde">Receitas</a> 
		<a href="despesa" class="vermelho">Despesas </a> 
		<a class="orcamento">Saldo: R$ <%= request.getAttribute("totalOrcamento") %></a>
	</div>
</body>
</html>