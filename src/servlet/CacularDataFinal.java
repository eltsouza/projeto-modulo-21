package servlet;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCalculaDataFinal;

@WebServlet("/pages/calcularDataFinal")
public class CacularDataFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DaoCalculaDataFinal daoCalculaDataFinal = new DaoCalculaDataFinal();
	
    public CacularDataFinal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		try {
			
			int horaDia= 8;
			Date dataCalculada = null;
			Double totalDias = 0.0;

			
			String data = request.getParameter("data");
			int tempo = Integer.parseInt(request.getParameter("tempo"));
			
			if(tempo <= horaDia) {
				Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateInformada);
				calendar.add(Calendar.DATE,1);
				dataCalculada = (Date) calendar.getTime();
				totalDias = 1.0;
			}else {
				totalDias = (double) (tempo / horaDia);
				
				if(totalDias <= 1) {
					dataCalculada = new SimpleDateFormat("dd/mm/yyyy").parse(data);
				}else {
					Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateInformada);
					calendar.add(Calendar.DATE, totalDias.intValue());
					
					dataCalculada = (Date) calendar.getTime();
				}				
			}
			
			daoCalculaDataFinal.gravaDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			
			RequestDispatcher dispacher = request.getRequestDispatcher("/pages/datas.jsp");
			request.setAttribute("dataFinal", new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
			request.setAttribute("dias",totalDias);
			dispacher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
	
	}

}
