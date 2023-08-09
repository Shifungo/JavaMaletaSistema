package armario.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;

public class CadastroItemMaletaDAO {
	
	public void VincularItemMaleta(int cod_Maleta, int cod_Item ) throws ExceptionDAO{
		String cod_MaletaS = String.valueOf(cod_Maleta);
		String cod_ItemS = String.valueOf(cod_Item);
		
		String sql = "insert into itens_maleta (fk_maletas , fk_itens) values (?,?)";
		
		PreparedStatement pStatement = null;
		Connection conn = null;
		
		try {
			conn = new ConnectionMVC().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, cod_MaletaS);
			pStatement.setString(2, cod_ItemS);
			pStatement.executeUpdate();
			System.out.println(cod_ItemS + cod_MaletaS + "deu certo");
			
		}catch (SQLException e) {
			throw new ExceptionDAO("erro no cadastro de item" + e);
		}finally {
			try {
				if(pStatement != null) {pStatement.close();}
			} catch (SQLException e) {
				throw new ExceptionDAO("erro ao fechar statement" + e);
			}
			try {
				if(conn != null) {conn.close();}
			} catch (SQLException e) {
				throw new ExceptionDAO("error ao fechar conn" + e);
			}
		}
		
		
		
	}

}
