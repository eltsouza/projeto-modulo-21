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
 * � necess�rio baixar a lib servel-api.jar e adiciona-l�
 * ao projeto dentro da pasta lib e posteriormente incluir dentro Build-Path
 * 
 * A Anota��o webFilter, faz que n�o seja necessario declarar o Filter no arquivo Webxml
 */
@WebFilter(urlPatterns={"/pages/*"}) 
public class FilterAutenticacao implements Filter{

	private static Connection connection;
	//private static Connection connectionBanco2;
	//private static Connection connectionBancoMysql;
	
	//� executado quando a aplica��o � derrubada
    @Override
	public void destroy() {
	}
		
    //Intercepta todas as requisi��es
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		System.out.println("Caminho da p�gina acessada: " + req.getServletPath());
		
		String urlParaAutenticar = req.getServletPath();
		
		
		//retorna null caso n�o esteja logado
		Usuario userLogado = (Usuario) session.getAttribute("usuario");//convers�o de cache
		
		if(userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/autenticar.jsp?url="+urlParaAutenticar);
			dispacher.forward(request,response);
			return;
		}
		
		//responsavel pela execucao do request e do response
		chain.doFilter(request, response);
		
		System.out.println("Interceptando a requisi��o");
	
	}
	
	//Executa quando a aplica��o � iniciada
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
		connection = ConnectionDataBase.getConnection();
		//connectionBanco2 = ConnectionDataBaseBanco2.getConnection();
		//connectionBancoMysql = ConnectionDataBaseMySQL.getConnection();
	}

}
