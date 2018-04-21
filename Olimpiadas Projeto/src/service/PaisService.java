package service;



import java.util.List;

import dao.PaisDAO;
import model.Pais;

public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public Pais criar(Pais pais) {
		return dao.criar(pais);
	}
	public Pais carregar(Pais pais) {
		return dao.carregar(pais);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}
	public List <Pais> listar (){
		return dao.listar();
	}
}
