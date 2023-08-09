package armario.model;
import armario.dao.ExceptionDAO;
import armario.dao.ItemsDAO;

public class Itens {
	private int cod_itens;
	private String nome_itens;
	private String desc_itens;
	
	public Itens(String nome_itens,int cod_itens, String desc_itens) {
		this.cod_itens = cod_itens;
		this.nome_itens = nome_itens;
		this.desc_itens = desc_itens;
	}

	

	
    public Itens(String item_nome, String item_desc) {
    	
		this.nome_itens = nome_itens;
		this.desc_itens = desc_itens;
	}




	public void setCod_itens(int cod_itens) {
        this.cod_itens = cod_itens;
    }

    public void setNome_itens(String nome_itens) {
        this.nome_itens = nome_itens;
    }

    public void setDesc_itens(String desc_itens) {
        this.desc_itens = desc_itens;
    }

    public int getCod_itens() {
        return cod_itens;
    }

    public String getNome_itens() {
        return nome_itens;
    }

    public String getDesc_itens() {
        return desc_itens;
    }
    
    public void cadastrarItem(Itens item){
    	try {
			new ItemsDAO().cadastrarItem(item);
		} catch (ExceptionDAO e) {
			
			e.printStackTrace();
		}
    	
    	
    }
    
    @Override 
    public String toString() {
    	
    	return this.getNome_itens();
    }
}
