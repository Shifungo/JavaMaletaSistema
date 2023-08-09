package armario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import armario.model.Itens;

public class ItemsDAO {

	
	
	public void cadastrarItem(Itens itens) throws ExceptionDAO{
		String sql = "insert into itens (nome_itens ,desc_itens)value(?,?) ";
	
		PreparedStatement pStatement = null;
		Connection conn = null;
	
		try {
			
			conn = new ConnectionMVC().getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, itens.getNome_itens() );
			pStatement.setString(2, itens.getDesc_itens());
			pStatement.executeUpdate();
			
		
			System.out.println("apos o commit");
		
		}catch (SQLException e){
			throw new ExceptionDAO("erro ao cadastrar maleta");
			
		}finally {
			
			try {
				if (pStatement != null ) {pStatement.close();}
			} catch (SQLException e2) {
				throw new ExceptionDAO("erro ao fechar statement" + e2);
			}
			try {
				if (conn != null) {conn.close();}
			} catch (SQLException e2) {
				throw new ExceptionDAO("erro ao fechar a conecçao" + e2);
			}
		}
	
	
	}
	
	public Itens[] getItemName() {
		ArrayList<Itens> itemNames = new ArrayList<Itens>();
		String queryString = "select nome_itens,cod_itens,desc_itens from itens";
	
	
		try (Connection conn = new ConnectionMVC().getConnection();) {
			
        	try (Statement stmt = conn.prepareStatement(queryString);
        		ResultSet rs = stmt.executeQuery(queryString)) {

            	while (rs.next()) {
                	
                	String name = rs.getString("nome_itens");
                	int cod = rs.getInt("cod_itens");
                	String desc= rs.getString("desc_itens");
                	
                	Itens itemNamesObject = new Itens(name, cod, desc);
                	
                	itemNames.add(itemNamesObject);
                	
                	System.out.println(itemNamesObject);

                	
            	}
            	
            	
        	}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		
		
		
		return itemNames.toArray(new Itens[0]);
		
		
		
	}
}
