<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Upload Arquivos</title>
		<script type="text/javascript" src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<h2>Upload Arquivos</h2>
		
		<form enctype="application/x-www-form-urlencoded">
			<input type="file" id="file" name="file" onchange="uploadFile();"/>
			<img alt="Imagem" src="" id="target" width="200" height="200">
		</form>

		<br/>
		<br/>
		<br/>
		<a href="fileUpload?acao=carregar">Carrregar</a>
		
		  <form action="fileUpload" method="get">	
			<table>
				<c:forEach items="${listaUserImagem}" var="user">
				   <tr>
				    <td>${user.id}</td>
				    <td>${user.login}</td>
				    <td><a target="_blank" href="fileUpload?acao=download&iduser=${user.id}">Download Arquivo</a> </td>
				   </tr>
				</c:forEach>
			</table>
		 </form>
		<br/>
		<br/>	
		
	</body>
	
	<script type="text/javascript">
	
	function uploadFile() {

		var target = document.querySelector('img');
		var file = document.querySelector('input[type=file]').files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;
			
			//-----Upload ajax------

			$.ajax({
				method : "POST",
				url : "fileUpload",
				data : { fileUpload : reader.result }
			}).done(function(response) {
				alert("Sucesso: " + response);
				
			}).fail(function(xhr, status, errorThrown) {
				alert("Error: " + xhr.responseText);
			});	

			//-----------
		};

		if (file) {
			reader.readAsDataURL(file); 
		} else {
			target.src = "";
		}

	}
	
	</script>
	
</html>