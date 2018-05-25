package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Modalidade;
import model.Olimpiada;
import model.Pais;
import service.ModalidadeService;
import service.OlimpiadaService;
import service.PaisService;

/**
 * Servlet implementation class ManterClienteController
 */
@WebServlet("/ManterOlimpiada.do")
public class ManterOlimpiadaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pAcao = request.getParameter("acao");
		PaisService ps = new PaisService();
		ModalidadeService ms = new ModalidadeService();
		OlimpiadaService os = new OlimpiadaService();
		
		if(pAcao.equals("criarPais")) {
			String pNome = request.getParameter("pais");
			int pPopulacao = Integer.parseInt(request.getParameter("populacao"));
			double pArea= Double.parseDouble(request.getParameter("area"));
			
			//instanciar o javabean
			Pais pais = new Pais();
			pais.setNome(pNome);
			pais.setPopulacao(pPopulacao);
			pais.setArea(pArea);
			System.out.println(pNome);
			System.out.println(pArea);
			System.out.println(pPopulacao);
			//instanciar o service
			
			ps.criar(pais);
			
			pais = ps.carregar(pais);
			
			
			request.setAttribute("pais", pais);
	        
	        RequestDispatcher view = 
	        request.getRequestDispatcher("Pais.jsp");
	        view.forward(request, response);
		}
		if(pAcao.equals("criarModalidade")) {
			String pNome = request.getParameter("modalidade");
			String pTipo = request.getParameter("tipo");
			
			//instanciar o javabean
			Modalidade modalidade = new Modalidade();
			Olimpiada olimpiada = new Olimpiada();
			modalidade.setNome(pNome);
			olimpiada.setTipo(pTipo);
			
			System.out.println(pNome);
			System.out.println(pTipo);

			//instanciar o service
			
			modalidade = ms.criar(modalidade, olimpiada);
			
			
			
			System.out.println(modalidade.getId());
			System.out.println(modalidade.getNome());
			
			request.setAttribute("modalidade", modalidade);
	        
	        RequestDispatcher view = 
	        request.getRequestDispatcher("Modalidade.jsp");
	        view.forward(request, response);
		}
		if(pAcao.equals("pesquisarOlimpiada")) {
			List<Pais> pais = ps.listar();
			List<Modalidade> modalidade = ms.listar();
			List<Olimpiada> olimpiada = os.listar();
			
			request.setAttribute("pais", pais);
			request.setAttribute("modalidade", modalidade);
			request.setAttribute("olimpiada", olimpiada);
			request.getRequestDispatcher("OlimpiadasPesquisar.jsp").forward(request, response);
		}
		if(pAcao.equals("carregarMedalhas")) {
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
			System.out.println(modalidade.getOuro());
			System.out.println(modalidade.getPrata());
			System.out.println(modalidade.getBronze());
			System.out.println(modalidade.getId());
			
			
			modalidade = os.carregar(pais, olimpiada, modalidade);
			request.setAttribute("pais", pais);
			request.setAttribute("modalidade", modalidade);
			request.setAttribute("olimpiada", olimpiada);
			request.getRequestDispatcher("OlimpiadasMedalhas.jsp").forward(request, response);
		}
		if(pAcao.equals("cadastrarOlimpiada")) {
			int idPais = Integer.parseInt(request.getParameter("pais"));
			int idModalidade = Integer.parseInt(request.getParameter("modalidade"));
			int ano	=	Integer.parseInt(request.getParameter("ano"));
			
			Olimpiada olimp = new Olimpiada();
			Modalidade mod = new Modalidade();
			Pais pais = new Pais();
			
			olimp.setAno(ano);
			mod.setId(idModalidade);
			pais.setId(idPais);
			
			mod = ms.carregar(mod);
			pais = ps.carregar(pais);
			
			request.setAttribute("pais", pais);
			request.setAttribute("modalidade", mod);
			request.setAttribute("olimpiada", olimp);
			request.getRequestDispatcher("CadastrarOlimpiada.jsp").forward(request, response);
		}
		if(pAcao.equals("criarOlimp")) {
			String pPais = request.getParameter("pais");
			int pOuro = Integer.parseInt(request.getParameter("ouro"));
			int pPrata = Integer.parseInt(request.getParameter("prata"));
			int pBronze = Integer.parseInt(request.getParameter("bronze"));
			String pModalidade = request.getParameter("modalidade");
			int ano	=	Integer.parseInt(request.getParameter("ano"));
			
			Olimpiada olimp = new Olimpiada();
			Modalidade mod = new Modalidade();
			Pais pais = new Pais();
			
			olimp.setAno(ano);
			mod.setNome(pModalidade);
			mod.setOuro(pOuro);
			mod.setPrata(pPrata);
			mod.setBronze(pBronze);
			pais.setNome(pPais);
			
			pais = ps.carregarNome(pais);
			mod = ms.carregarNome(mod);
			
			os.criar(pais, olimp, mod);
			

			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if(pAcao.equals("excluirOlimp")) {
			int idPais = Integer.parseInt(request.getParameter("pais"));
			int idModalidade = Integer.parseInt(request.getParameter("modalidade"));
			int ano	=	Integer.parseInt(request.getParameter("ano"));
			
			System.out.println(idPais);
			System.out.println(idModalidade);
			System.out.println(ano);
			
			Olimpiada olimp = new Olimpiada();
			Modalidade mod = new Modalidade();
			Pais pais = new Pais();
			
			olimp.setAno(ano);	
			mod.setId(idModalidade);
			pais.setId(idPais);
			
			//System.out.println(olimp.getAno());
			//System.out.println(mod.getId());
			//System.out.println(pais.getId());
			
			os.excluir(pais, mod, olimp);
			
			
			

			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if(pAcao.equals("editarOlimpiadas")) {
			int idPais = Integer.parseInt(request.getParameter("pais"));
			int idModalidade = Integer.parseInt(request.getParameter("modalidade"));
			int ano	=	Integer.parseInt(request.getParameter("ano"));
			String paisNome = request.getParameter("paisNome");
			String modalidadeNome = request.getParameter("modalidadeNome");
			int ouro 	=	Integer.parseInt(request.getParameter("ouro"));
			int prata 	=	Integer.parseInt(request.getParameter("prata"));
			int bronze 	=	Integer.parseInt(request.getParameter("bronze"));
			
			System.out.println(idPais);
			System.out.println(idModalidade);
			System.out.println(ano);
			
			Olimpiada olimp = new Olimpiada();
			Modalidade mod = new Modalidade();
			Pais pais = new Pais();
			
			olimp.setAno(ano);	
			mod.setId(idModalidade);
			mod.setOuro(ouro);
			mod.setPrata(prata);
			mod.setBronze(bronze);
			mod.setNome(modalidadeNome);
			pais.setNome(paisNome);
			pais.setId(idPais);	
			
			request.setAttribute("pais", pais);
			request.setAttribute("modalidade", mod);
			request.setAttribute("olimpiada", olimp);
			request.getRequestDispatcher("EditarOlimp.jsp").forward(request, response);
		}
		if(pAcao.equals("editarOlimp")) {
			int idPais = Integer.parseInt(request.getParameter("pais"));
			int idModalidade = Integer.parseInt(request.getParameter("modalidade"));
			int ano	=	Integer.parseInt(request.getParameter("ano"));
			int ouro 	=	Integer.parseInt(request.getParameter("ouro"));
			int prata 	=	Integer.parseInt(request.getParameter("prata"));
			int bronze 	=	Integer.parseInt(request.getParameter("bronze"));
			
			System.out.println(idPais);
			System.out.println(idModalidade);
			System.out.println(ano);
			
			Olimpiada olimp = new Olimpiada();
			Modalidade mod = new Modalidade();
			Pais pais = new Pais();
			
			olimp.setAno(ano);	
			mod.setId(idModalidade);
			mod.setOuro(ouro);
			mod.setPrata(prata);
			mod.setBronze(bronze);
			pais.setId(idPais);
			
			os.atualizar(pais, mod, olimp);
			
			request.setAttribute("pais", pais);
			request.setAttribute("modalidade", mod);
			request.setAttribute("olimpiada", olimp);
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
	}
}
