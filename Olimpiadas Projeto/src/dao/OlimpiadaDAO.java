package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.Modalidade;
import model.Olimpiada;
import model.Pais;

public class OlimpiadaDAO {
	public void criar(Pais pais, Olimpiada olimpiada, Modalidade modalidade) {
		String sqlInsert = "INSERT INTO olimpiada(ouro, prata, bronze, pais_idpais, modalidade_idmodalidade, ano_idano) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, modalidade.getOuro());
			stm.setInt(2, modalidade.getPrata());
			stm.setInt(3, modalidade.getBronze());
			stm.setInt(4, pais.getId());
			stm.setInt(5, modalidade.getId());
			stm.setInt(6, olimpiada.getAno());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Olimpiada> listar(){
		List <Olimpiada> olimpiada= new ArrayList <>();
		String sql = "SELECT * FROM ano";
		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
				try {
					while (rs.next()) {
						int    id   	= rs.getInt("idano");
						String tipo = rs.getString("tipo");

						Olimpiada olimp = new Olimpiada();
						olimp.setAno(id);
						olimp.setTipo(tipo);
						olimpiada.add(olimp);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return olimpiada;
	}
	public Modalidade carregar(Pais pais, Olimpiada olimpiada, Modalidade modalidade) {
		String sqlSelect = "SELECT ouro, prata, bronze FROM olimpiada WHERE pais_idpais = ? and modalidade_idmodalidade = ? and ano_idano = ?";
		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			stm.setInt(2, modalidade.getId());
			stm.setInt(3, olimpiada.getAno());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					modalidade.setOuro(rs.getInt("ouro"));
					modalidade.setPrata(rs.getInt("prata"));
					modalidade.setBronze(rs.getInt("bronze"));
				} else {
					modalidade.setOuro(-1);
					modalidade.setPrata(-1);
					modalidade.setBronze(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return modalidade;
	}
	public void excluir(Pais pais, Modalidade modalidade, Olimpiada olimpiada) {
		String sqlDelete = "DELETE FROM olimpiada WHERE pais_idpais = ? and modalidade_idmodalidade = ? and ano_idano = ?";
		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, pais.getId());
			stm.setInt(2, modalidade.getId());
			stm.setInt(3, olimpiada.getAno());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Pais pais, Modalidade modalidade, Olimpiada olimpiada){
		String sqlUpdate = "UPDATE olimpiada SET ouro=?, prata=?, bronze=? WHERE pais_idpais=? and modalidade_idmodalidade=? and ano_idano=?";
		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, modalidade.getOuro());
			stm.setInt(2, modalidade.getPrata());
			stm.setInt(3, modalidade.getBronze());
			stm.setInt(4, pais.getId());
			stm.setInt(5, modalidade.getId());
			stm.setInt(6, olimpiada.getAno());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
