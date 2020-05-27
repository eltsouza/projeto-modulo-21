package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;

@WebServlet("/pages/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAutenticacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		if(Boolean.parseBoolean(request.getParameter("logout"))) {
		
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.invalidate();
			response.sendRedirect("../index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		// nete momento poderia ser feito uma validacao no BD
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("1234")) {
			//redireciona para o sistema 
			
			//Passando pelo filter e colocando o usuario na sessão;
			Usuario userLogado = new Usuario();
			userLogado.setLogin(login);
			userLogado.setSenha(senha);
			
			//adiciona usuario logado na sessão
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("usuario", userLogado);
			
			RequestDispatcher dispacher = request.getRequestDispatcher(url);
			dispacher.forward(request,response);
		}else {
			//redireciona para login 
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/autenticar.jsp");
			dispacher.forward(request,response);
		}
		
	}

}
