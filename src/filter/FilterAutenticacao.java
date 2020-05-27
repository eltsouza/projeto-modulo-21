package filter;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import connection.ConnectionDataBase;
import connection.ConnectionDataBaseMySQL;
import entidades.Usuario;

/*
 * Para trabalhar com filtro (Open Session View)
 * é necessário baixar a lib servel-api.jar e adiciona-lá
 * ao projeto dentro da pasta lib e posteriormente incluir dentro Build-Path
 * 
 * A Anotação webFilter, faz que não seja necessario declarar o Filter no arquivo Webxml
 */
@WebFilter(urlPatterns={"/pages/*"}) 
public class FilterAutenticacao implements Filter{

	private static Connection connection;
	//private static Connection connectionBanco2;
	//private static Connection connectionBancoMysql;
	
	//É executado quando a aplicação é derrubada
    @Override
	public void destroy() {
	}
		
    //Intercepta todas as requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		System.out.println("Caminho da página acessada: " + req.getServletPath());
		
		String urlParaAutenticar = req.getServletPath();
		
		
		//retorna null caso não esteja logado
		Usuario userLogado = (Usuario) session.getAttribute("usuario");//conversão de cache
		
		if(userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/autenticar.jsp?url="+urlParaAutenticar);
			dispacher.forward(request,response);
			return;
		}
		
		//responsavel pela execucao do request e do response
		chain.doFilter(request, response);
		
		System.out.println("Interceptando a requisição");
	
	}
	
	//Executa quando a aplicação é iniciada
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
		connection = ConnectionDataBase.getConnection();
		//connectionBanco2 = ConnectionDataBaseBanco2.getConnection();
		//connectionBancoMysql = ConnectionDataBaseMySQL.getConnection();
	}

}
