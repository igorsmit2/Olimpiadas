package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Modalidade;
import model.Olimpiada;
import service.ModalidadeService;



public class CadastrarModalidade implements Command{
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ModalidadeService ms = new ModalidadeService();

		Modalidade modalidade = new Modalidade();
		Olimpiada olimpiada = new Olimpiada();
		
		String pNome = request.getParameter("modalidade");
		String pTipo = request.getParameter("tipo");
		
		modalidade.setNome(pNome);
		olimpiada.setTipo(pTipo);
		
		
			
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		
		session.setAttribute("modalidade", modalidade);
		
		view = request.getRequestDispatcher("Pais.jsp");
		
		view.forward(request, response);
	}
}
