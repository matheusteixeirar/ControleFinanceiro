package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/DespesaController", "/despesa", "/insertDespesa", "/selectDespesa", "/updateDespesa", "/deleteDespesa" }) 
public class DespesaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans transacao = new JavaBeans();
        
    public DespesaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/despesa")) { 
			despesas(request, response);
		} else if (action.equals("/insertDespesa")) {
			adicionarDespesa(request, response);
		} else if (action.equals("/selectDespesa")) {
			carregarDespesa(request, response);
		} else if (action.equals("/updateDespesa")) {
			editarDespesa(request, response);
		} else if (action.equals("/deleteDespesa")) {
			removerDespesa(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	/** Listar despesas **/
	protected void despesas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setTipo("despesa");
		ArrayList<JavaBeans> lista = dao.listarTransacoes(transacao);
		request.setAttribute("listaDespesas", lista);
		int totalDespesas = dao.somarTotalTransacoes("despesa");
		request.setAttribute("totalDespesas", totalDespesas);
		RequestDispatcher rd = request.getRequestDispatcher("despesas.jsp");
		rd.forward(request, response);
	}
	
	/** Nova despesa **/
	protected void adicionarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setTipo("despesa");
		transacao.setValor(request.getParameter("valor"));
		transacao.setCategoria(request.getParameter("categoria"));
		transacao.setDescricao(request.getParameter("descricao"));
		transacao.setData(request.getParameter("data"));
		dao.criarTransacao(transacao);
		response.sendRedirect("despesa");
	}
	
	/** Editar despesa **/
	protected void carregarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		transacao.setId(id);
		dao.selecionarTransacao(transacao);
		request.setAttribute("id", id);
		request.setAttribute("valor", transacao.getValor());
		request.setAttribute("categoria", transacao.getCategoria());
		request.setAttribute("descricao", transacao.getDescricao());
		request.setAttribute("data", transacao.getData());
		RequestDispatcher rd = request.getRequestDispatcher("editarDespesa.jsp");
		rd.forward(request, response);
		
	}
	
	protected void editarDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setId(request.getParameter("id"));
		transacao.setTipo(request.getParameter("tipo"));
		transacao.setValor(request.getParameter("valor"));
		transacao.setCategoria(request.getParameter("categoria"));
		transacao.setDescricao(request.getParameter("descricao"));
		transacao.setData(request.getParameter("data"));
		dao.alterarTransacao(transacao);
		response.sendRedirect("despesa");;
	}
	
	/** Deletar despesa **/
	protected void removerDespesa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		transacao.setId(id);
		dao.deletarTransacao(transacao);
		response.sendRedirect("despesa");
	}
	
}
