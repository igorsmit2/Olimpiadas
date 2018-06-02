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

public class CarregarMedalhas implements Command{
	
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OlimpiadaService os = new OlimpiadaService();
		PaisService ps = new PaisService();
		ModalidadeService ms = new ModalidadeService();
		
		int idPais = Integer.parseInt(request.getParameter("pais"));
		int idModalidade = Integer.parseInt(request.getParameter("modalidade"));
		int ano	=	Integer.parseInt(request.getParameter("ano"));
		
		System.out.println(idPais);
		System.out.println(idModalidade);
		System.out.println(ano);
		
		Modalidade modalidade = new Modalidade();
		Olimpiada olimpiada = new Olimpiada();
		Pais pais = new Pais();
		pais.setId(idPais);
		pais = ps.carregar(pais);
		modalidade.setId(idModalidade);
		modalidade = ms.carregar(modalidade);
		olimpiada.setAno(ano);	
		
		modalidade = os.carregar(pais, olimpiada, modalidade);
		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		
		session.setAttribute("pais", pais);
		session.setAttribute("modalidade", modalidade);
		session.setAttribute("olimpiada", olimpiada);
		
		view = request.getRequestDispatcher("OlimpiadasMedalhas.jsp");
		
		view.forward(request, response);
	}

}
