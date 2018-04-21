package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Modalidade;
import model.Olimpiada;
import model.Pais;

public class ModalidadeDAO {
	// INSERT
	public Modalidade criar(Modalidade modalidade, Olimpiada olimpiada) {
		String sqlInsert = "INSERT INTO modalidade(nome, tipo) VALUES ( ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, modalidade.getNome());
			stm.setString(2, olimpiada.getTipo());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					modalidade.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modalidade;
	}
	
	public Modalidade carregar(Modalidade modalidade) {
		String sqlSelect = "SELECT nome FROM modalidade WHERE idmodalidade = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, modalidade.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					modalidade.setNome(rs.getString("nome"));
				} else {
					modalidade.setId(-1);
					modalidade.setNome(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return modalidade;
	}
	
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM modalidade WHERE idmodalidade = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Modalidade modalidade, Olimpiada olimpiada) {
		String sqlUpdate = "UPDATE modalidade SET nome=?, tipo=?, WHERE idmodalidade=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, modalidade.getNome());
			stm.setString(2, olimpiada.getTipo());
			stm.setInt(3, modalidade.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
