/**
 * Confirmação de exclusão de uma transação
 */

function confirmarExclusaoReceita(id) {
	let resposta = confirm("Confirmar a exclusão dessa receita?")
	if (resposta === true) {
		window.location.href = "deleteReceita?id=" + id
	}
}

function confirmarExclusaoDespesa(id) {
	let resposta = confirm("Confirmar a exclusão dessa despesa?")
	if (resposta === true) {
		window.location.href = "deleteDespesa?id=" + id
	}
}