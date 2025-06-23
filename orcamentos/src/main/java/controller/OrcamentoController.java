package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;

@WebServlet(urlPatterns = { "/OrcamentoController", "/main" }) 
public class OrcamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAO dao = new DAO();
       
    public OrcamentoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) { 
			transacoes(request, response);
		}
	}
	
	protected void transacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int totalReceitas = dao.somarTotalTransacoes("receita");
	    int totalDespesas = dao.somarTotalTransacoes("despesa");
	    int orcamento = totalReceitas - totalDespesas;
	    request.setAttribute("totalOrcamento", orcamento);
	    RequestDispatcher rd = request.getRequestDispatcher("orcamento.jsp");
	    rd.forward(request, response);
	}
	
}
