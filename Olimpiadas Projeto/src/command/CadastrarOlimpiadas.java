package command;

import java.io.IOException;
import java.util.List;

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

public class CadastrarOlimpiadas implements Command{
	
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PaisService ps = new PaisService();
		ModalidadeService ms = new ModalidadeService();
		OlimpiadaService os = new OlimpiadaService();
		
		String pPais = request.getParameter("pais");
		int pOuro = Integer.parseInt(request.getParameter("ouro"));
		int pPrata = Integer.parseInt(request.getParameter("prata"));
		int pBronze = Integer.parseInt(request.getParameter("bronze"));
		String pModalidade = request.getParameter("modalidade");
		int ano	=	Integer.parseInt(request.getParameter("ano"));
		
		Olimpiada olimpiada = new Olimpiada();
		Modalidade modalidade = new Modalidade();
		Pais pais = new Pais();
		
		olimpiada.setAno(ano);
		modalidade.setNome(pModalidade);
		modalidade.setOuro(pOuro);
		modalidade.setPrata(pPrata);
		modalidade.setBronze(pBronze);
		pais.setNome(pPais);
		
		pais = ps.carregarNome(pais);
		modalidade = ms.carregarNome(modalidade);
		
		os.criar(pais, olimpiada, modalidade);
		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		
		session.setAttribute("pais", pais);
		session.setAttribute("modalidade", modalidade);
		session.setAttribute("olimpiada", olimpiada);
		
		view = request.getRequestDispatcher("index.html");
		
		view.forward(request, response);
	}
}
