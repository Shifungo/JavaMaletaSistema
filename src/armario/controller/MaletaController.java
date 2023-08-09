package armario.controller;

import java.sql.SQLException;

import armario.dao.ExceptionDAO;
import armario.dao.ItemsDAO;
import armario.dao.MaletaDAO;
import armario.model.Maletas;
import armario.model.Itens;
/**
 *
 * @author jeans
 */
public class MaletaController {
    public boolean cadastrarMaleta(String nome_maleta){
        
        if (nome_maleta != null && nome_maleta.length() > 0){
            
            Maletas maleta = new Maletas(1 ,nome_maleta); 
            try {
            	 maleta.casdastrarMaleta(maleta);
			} catch (ExceptionDAO e) {
				return false; 
			}
           
            
           
            return true;
        }else{
            return false;
        }
        
    }  
    public Maletas[] getMaletaList() {
    	MaletaDAO maletas = new MaletaDAO();
    	return maletas.getMaletaName();
    }
    
    
    public Itens[] getItensNaMaleta(int maleta_id) throws SQLException {
    	
    	return new MaletaDAO().getItensNaMaleta(maleta_id);
    	
    	
    }
    public int getMaletaIdByName(String maletaString) throws SQLException {
    	return new MaletaDAO().getMaletaIdByName(maletaString);
    }
    public Maletas findMaletasByName(String maletaName) {
        Maletas[] maletasList = getMaletaList(); 

        for (Maletas maletas : maletasList) {
            if (maletas.getNome_maleta().equals(maletaName)) {
                return maletas;
            }
        }

        return null; 
    }
    
}
