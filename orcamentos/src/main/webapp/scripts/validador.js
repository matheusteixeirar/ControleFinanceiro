/**
 * Validar campos e valores obrigatórios
 */

function validar() {
	let valor = form.valor.value.trim()
	let categoria = form.categoria.value.trim()
	let data = form.data.value
	
	let valorN = parseInt(valor)
	
	if (valor === "" || valorN <= 0 || isNaN(valorN)) {
		alert('Preencha o campo valor corretamente (apenas números maiores que zero)')
		form.valor.focus()
		return false
	} else if (categoria === "") {
		alert('Preencha o campo categoria')
		form.categoria.focus()
		return false
	} else if (data === "") {
		alert('Preencha o campo data')
		form.data.focus()
		return false
	} else {
		document.forms["form"].submit()
	}
	

}