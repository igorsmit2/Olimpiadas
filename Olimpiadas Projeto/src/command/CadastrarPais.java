package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Modalidade;
import model.Olimpiada;
import model.Pais;
import service.ModalidadeService;
import service.OlimpiadaService;
import service.PaisService;

public class CadastrarPais implements Command{
	
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PaisService ps = new PaisService();

		Pais pais = new Pais();
		
		String pNome = request.getParameter("pais");
		int pPopulacao = Integer.parseInt(request.getParameter("populacao"));
		double pArea= Double.parseDouble(request.getParameter("area"));
		
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);
		
		ps.criar(pais);
		
		pais = ps.carregar(pais);
			
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		
		session.setAttribute("pais", pais);
		
		view = request.getRequestDispatcher("Pais.jsp");
		
		view.forward(request, response);
	}
}
