package service;

import dao.OlimpiadaDAO;
import model.Modalidade;
import model.Olimpiada;
import model.Pais;

public class OlimpiadaService {
	OlimpiadaDAO dao = new OlimpiadaDAO();
	
	public void criar(Pais pais, Olimpiada olimpiada, Modalidade modalidade) {
		dao.criar(pais, olimpiada, modalidade);
	}

	public Modalidade carregar(Pais pais, Olimpiada olimpiada, Modalidade modalidade){
		return dao.carregar(pais, olimpiada, modalidade);
	}
	
	public void excluir(Pais pais, Modalidade modalidade, Olimpiada olimpiada) {
		dao.excluir(pais, modalidade, olimpiada);
	}
	public void atualizar(Pais pais, Modalidade modalidade, Olimpiada olimpiada) {
		dao.atualizar(pais, modalidade, olimpiada);
	}
}
