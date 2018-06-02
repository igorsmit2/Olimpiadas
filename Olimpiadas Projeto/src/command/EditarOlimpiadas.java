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

public class EditarOlimpiadas implements Command{
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PaisService ps = new PaisService();
		ModalidadeService ms = new ModalidadeService();
		OlimpiadaService os = new OlimpiadaService();
		
		int idPais 			= 	Integer.parseInt(request.getParameter("pais"		));
		int idModalidade 	= 	Integer.parseInt(request.getParameter("modalidade"	));
		int ano				= 	Integer.parseInt(request.getParameter("ano"			));
		int ouro 			= 	Integer.parseInt(request.getParameter("ouro"		));
		int prata 			= 	Integer.parseInt(request.getParameter("prata"		));
		int bronze 			= 	Integer.parseInt(request.getParameter("bronze"		));
		
		System.out.println(idPais		);
		System.out.println(idModalidade	);
		System.out.println(ano			);
		
		Olimpiada 	olimpiada 	= new Olimpiada();
		Modalidade 	modalidade 	= new Modalidade();
		Pais pais = new Pais();
		
		olimpiada.setAno	(ano			);	
		modalidade.setId		(idModalidade	);
		modalidade.setOuro		(ouro			);
		modalidade.setPrata	(prata			);
		modalidade.setBronze	(bronze			);
		pais.setId		(idPais			);
		
		os.atualizar(pais, modalidade, olimpiada);
		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
		
		session.setAttribute("pais", pais);
		session.setAttribute("modalidade", modalidade);
		session.setAttribute("olimpiada", olimpiada);
		
		view = request.getRequestDispatcher("index.html");
		
		view.forward(request, response);
	}
}
