<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Capturar Exceções</title>
		<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<h2>Capturando Exceções com Jquery</h2>
		<input type="text" value="Valor Informador" id="txtValor">
		<input type="button" onclick="TestarExcecao();" value="Testar Exceção">

	</body>
	
	<script type="text/javascript">	
		function TestarExcecao() {
			valorInformado = $('#txtValor').val();
			
			//Jquery c/ Ajax
			
			$.ajax({
				method: "POST",
				url: "capturarExcecao", // servelt que intercepta a requisição
				data: {valorParam: valorInformado}
			})
				.done(function(response){ // response OK
					alert("Sucesso :" + response);		
				
				//fazer algo se OK
			})
			.fail(function(xhr, status, errorThrown){ // response erro
				alert("Error :" + xhr.responseText);//mostra o valor da resposta do servidor
			
			    //fazer algo se falhar
		});			

		}	
	</script>
	
</html>