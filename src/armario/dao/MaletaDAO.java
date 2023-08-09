package armario.dao;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.swing.event.ListSelectionListener;

import armario.model.Itens;
import armario.model.Maletas;

public class MaletaDAO {

	public void cadastrarMaleta(Maletas maletas) throws ExceptionDAO {
		String sql = "insert into maleta (nome_maleta) value(?)";

		PreparedStatement pStatement = null;
		Connection conn = null;

		try {
			conn = new ConnectionMVC().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, maletas.getNome_maleta());
			pStatement.executeUpdate();
			System.out.println(pStatement);

			System.out.println("apos o commit");

		} catch (SQLException e) {
			throw new ExceptionDAO("erro ao cadastrar maleta");

		} finally {

			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e2) {
				throw new ExceptionDAO("erro ao fechar statement" + e2);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				throw new ExceptionDAO("erro ao fechar a conecçao" + e2);
			}
		}

	}

	public Maletas[] getMaletaName() {
		ArrayList<Maletas> maletaNames = new ArrayList<>();
		String queryString = "select cod_maleta,nome_maleta from maleta";

		try (Connection conn = new ConnectionMVC().getConnection();) {

			try (Statement stmt = conn.prepareStatement(queryString); ResultSet rs = stmt.executeQuery(queryString)) {

				while (rs.next()) {
					int cod = rs.getInt("cod_maleta");
					String name = rs.getString("nome_maleta");

					Maletas maletasObj = new Maletas(cod, name);

					maletaNames.add(maletasObj);

					System.out.println(", Name: " + name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return maletaNames.toArray(new Maletas[0]);
	}

	public Itens[] getItensNaMaleta(int maleta_id) throws SQLException {
		ArrayList<Itens> itemLista = new ArrayList<>();
		System.out.println("got here" + maleta_id);
		String queryString = "SELECT itens.* FROM itens_maleta INNER JOIN itens ON itens.cod_itens = itens_maleta.fk_itens WHERE itens_maleta.fk_maletas = ?";

		try (Connection conn = new ConnectionMVC().getConnection();
				PreparedStatement stmt = conn.prepareStatement(queryString)) {
			stmt.setInt(1, maleta_id);

			try (ResultSet rSet = stmt.executeQuery()) {

				while (rSet.next()) {
					Itens item = new Itens(rSet.getString("nome_itens"), rSet.getInt("cod_itens"), rSet.getString("desc_itens"));

					itemLista.add(item);

				}

			} catch (SQLException e) {
				throw e;
			}
		}
		System.out.println(itemLista);
		return itemLista.toArray(new Itens[0]);

	}

	public int getMaletaIdByName(String maleta_nome) throws SQLException {
		int maleta_id;

		String querString = ("select cod_maleta from maleta where nome_maleta = ?  ");

		try (Connection connection = new ConnectionMVC().getConnection();
				PreparedStatement stmt = connection.prepareStatement(querString)) {
			stmt.setString(1, maleta_nome);
			try(ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
	                maleta_id = resultado.getInt("cod_maleta");
	            } else {
	                // Handle the case when no result is found, e.g., throw an exception or set a default value.
	                // For simplicity, we'll set it to -1 here.
	                maleta_id = -1;
	            }
			} catch (SQLException e) {
				throw e;
			}
			
			
		} catch (SQLException e) {
			throw new SQLException("erro ao pegar ids " + e);
		}

		return maleta_id;
	}

}
