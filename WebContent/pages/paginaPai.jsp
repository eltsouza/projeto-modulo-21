<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Página Pai</title>
		<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
	
		<h2>Página Pai load jQuery</h2>
		
		<input type="button" value="Carregar página" onClick="carregar();">
		
		<div id="mostrarPaginaFilha"></div> <!-- Local de carregamento da pagina filha -->
		
	
	</body>
	<script type="text/javascript">
		function carregar(){
			$("#mostrarPaginaFilha").load('paginaFilha.jsp'); //carrega pagina filha com jQuery
		}
	</script>
</html>