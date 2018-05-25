package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Olimpiada {
	private int ano;
	private String tipo;
		
		// Constructor.
	public Olimpiada() {
		super();
	}
	public Olimpiada(int ano, String tipo) {
		setAno(ano);
		setTipo(tipo);
	}
	
		// Getters And Setters
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		if (ano != other.ano)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
		// Obtém conexão com o banco de dados
	public Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost/olimpBd?user=root&password=");
	}
	
		// INSERT
	public void criar(Pais pais, Olimpiada olimpiada, Modalidade modalidade) {
		String sqlInsert = "INSERT INTO olimpiada(ouro, prata, bronze, pais_idpais, modalidade_idmodalidade, ano_idano) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = obtemConexao();
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
	
		// SELECT
	public Modalidade carregar(Pais pais, Olimpiada olimpiada, Modalidade modalidade) {
		String sqlSelect = "SELECT ouro, prata, bronze FROM olimpiada WHERE pais_idpais = ? and modalidade_idmodalidade = ? and ano_idano = ?";
		try (Connection conn = obtemConexao();
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
	
		// DELETE
	public void excluir(Pais pais, Modalidade modalidade, Olimpiada olimpiada) {
		String sqlDelete = "DELETE FROM olimpiada WHERE pais_idpais = ? and modalidade_idmodalidade = ? and ano_idano = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, pais.getId());
			stm.setInt(2, modalidade.getId());
			stm.setInt(3, olimpiada.getAno());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		// UPDATE
	public void atualizar(Pais pais, Modalidade modalidade, Olimpiada olimpiada){
		String sqlUpdate = "UPDATE olimpiada SET ouro=?, prata=?, bronze=? WHERE pais_idpais=? and modalidade_idmodalidade=? and ano_idano=?";
		try (Connection conn = obtemConexao();
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
