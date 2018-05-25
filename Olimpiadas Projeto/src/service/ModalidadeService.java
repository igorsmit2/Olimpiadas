package service;
import java.util.List;

import dao.ModalidadeDAO;
import model.Modalidade;
import model.Olimpiada;

public class ModalidadeService {
	ModalidadeDAO dao = new ModalidadeDAO();
	
	public Modalidade criar(Modalidade modalidade, Olimpiada olimpiada) {
		return dao.criar(modalidade, olimpiada);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
	public Modalidade carregar(Modalidade modalidade) {
		return dao.carregar(modalidade);
	}
	public Modalidade carregarNome(Modalidade modalidade) {
		return dao.carregarNome(modalidade);
	}
	public void atualizar(Modalidade modalidade, Olimpiada olimpiada) {
		dao.atualizar(modalidade, olimpiada);
	}
	public List<Modalidade> listar(){
		return dao.listarModalidade();
	}
}
