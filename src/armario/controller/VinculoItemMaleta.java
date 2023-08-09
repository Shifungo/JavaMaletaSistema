package armario.controller;
import armario.dao.ExceptionDAO;
import armario.model.Item_maleta;
import armario.model.Itens;
import armario.model.Maletas;



public class VinculoItemMaleta {
	
	
	public void VincularItemMaleta(Maletas maleta, Itens item) throws ExceptionDAO {
		
		
		int item_id = item.getCod_itens();
		
		int maleta_id = maleta.getCod_maleta();
		
		Item_maleta item_maleta = new Item_maleta();
		
		try {
			item_maleta.cadastrarItem(maleta_id, item_id);
		} catch (ExceptionDAO e) {
			throw new ExceptionDAO("erro na controller" + e);
		}
		
		
		
		
	}

}
