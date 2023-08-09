package armario.model;
import armario.dao.CadastroItemMaletaDAO;
import armario.dao.ExceptionDAO;

public class Item_maleta {
	private int cod_maleta;
	private int cod_item;

    public void setCod_maleta(int cod_maleta) {
        this.cod_maleta = cod_maleta;
    }

    public void setCod_item(int cod_item) {
        this.cod_item = cod_item;
    }

    public int getCod_maleta() {
        return cod_maleta;
    }

    public int getCod_item() {
        return cod_item;
    }
    
    public void cadastrarItem(int maleta_cod, int item_cod) throws ExceptionDAO{
    	CadastroItemMaletaDAO cadastroItemMaletaDAO = new CadastroItemMaletaDAO();
    	try {
    		cadastroItemMaletaDAO.VincularItemMaleta(maleta_cod,item_cod);
		} catch (ExceptionDAO e) {
			throw new ExceptionDAO("erro na model" + e);
		}
    	
    	
    }
}
