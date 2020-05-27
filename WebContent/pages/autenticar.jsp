<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Autenticar</title>
	</head>
	<body>
		<h1>Autenticar Usuário</h1>	
		
		<form action="ServletAutenticacao" method="post">
			<input type="hidden"url" name="url" value="<%=request.getParameter("url")%>">
			<table>
				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"></td>
				</tr>
				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"></td>
				</tr>
				<tr>
					<td><input type="submit" id="logar" name="logar" value="logar"></td>
				</tr>
			</table>
		</form>
	</body>
</html>