package excecoes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/capturarExcecao")
public class CapturarExcecao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CapturarExcecao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		try {
			String valor = request.getParameter("valorParam");
			
			Integer.parseInt(valor);
			
			response.setStatus(200);// ok nenhum erro
			response.getWriter().write("Processado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500); // erro interno do servidor
			response.getWriter().write("Erro no processamento :" + e.getMessage());
		}
	}

}
