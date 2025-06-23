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

@WebServlet(urlPatterns = { "/ReceitaController", "/receita", "/insertReceita", "/selectReceita", "/updateReceita", "/deleteReceita" }) 
public class ReceitaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans transacao = new JavaBeans();
        
    public ReceitaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/receita")) { 
			receitas(request, response);
		} else if (action.equals("/insertReceita")) {
			adicionarReceita(request, response);
		} else if (action.equals("/selectReceita")) {
			carregarReceita(request, response);
		} else if (action.equals("/updateReceita")) {
			editarReceita(request, response);
		} else if (action.equals("/deleteReceita")) {
			removerReceita(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	/** Listar receitas **/
	protected void receitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setTipo("receita");
		ArrayList<JavaBeans> lista = dao.listarTransacoes(transacao);
		request.setAttribute("listaReceitas", lista);
	    int totalReceitas = dao.somarTotalTransacoes("receita");
	    request.setAttribute("totalReceitas", totalReceitas);
		RequestDispatcher rd = request.getRequestDispatcher("receitas.jsp");
		rd.forward(request, response);
	}
	
	/** Nova receita **/
	protected void adicionarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setTipo("receita");
		transacao.setValor(request.getParameter("valor"));
		transacao.setCategoria(request.getParameter("categoria"));
		transacao.setDescricao(request.getParameter("descricao"));
		transacao.setData(request.getParameter("data"));
		dao.criarTransacao(transacao);
		response.sendRedirect("receita");
	}
	
	/** Editar receita **/
	protected void carregarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		transacao.setId(id);
		dao.selecionarTransacao(transacao);
		request.setAttribute("id", id);
		request.setAttribute("valor", transacao.getValor());
		request.setAttribute("categoria", transacao.getCategoria());
		request.setAttribute("descricao", transacao.getDescricao());
		request.setAttribute("data", transacao.getData());
		RequestDispatcher rd = request.getRequestDispatcher("editarReceita.jsp");
		rd.forward(request, response);
		
	}
	
	protected void editarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transacao.setId(request.getParameter("id"));
		transacao.setTipo(request.getParameter("tipo"));
		transacao.setValor(request.getParameter("valor"));
		transacao.setCategoria(request.getParameter("categoria"));
		transacao.setDescricao(request.getParameter("descricao"));
		transacao.setData(request.getParameter("data"));
		dao.alterarTransacao(transacao);
		response.sendRedirect("receita");;
	}
	
	/** Deletar receita **/
	protected void removerReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		transacao.setId(id);
		dao.deletarTransacao(transacao);
		response.sendRedirect("receita");
	}
	
}
