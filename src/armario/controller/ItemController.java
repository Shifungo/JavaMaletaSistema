
package armario.controller;
import armario.model.Itens;
import armario.dao.ItemsDAO;

public class ItemController {
    
    public boolean cadastrarItem(String item_nome, String item_desc){
        
        if(item_nome != null && item_nome.length() > 0){
            Itens item = new Itens(item_nome,0, item_desc);
            System.out.println(item);
            item.cadastrarItem(item); 
            return true;
        }
        return false;
        
        
    }
    public Itens[] getItemNames() {
    	
    	ItemsDAO itemsDAO = new ItemsDAO();
    	
    	return itemsDAO.getItemName();
    	
    }
}
