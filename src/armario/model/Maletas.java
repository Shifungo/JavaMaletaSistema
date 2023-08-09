package armario.model;
import java.util.Objects;

import armario.dao.ExceptionDAO;
import armario.dao.MaletaDAO;

public class Maletas {
	private int cod_maleta;
	private String nome_maleta;
	
	public Maletas (int cod_maleta , String nome_maleta) {
		this.cod_maleta = cod_maleta; 
		this.nome_maleta = nome_maleta;
	}

    

	public int getCod_maleta() {
        return cod_maleta;
    }

    public String getNome_maleta() {
        return nome_maleta;
    }

    public void setCod_maleta(int cod_maleta) {
        this.cod_maleta = cod_maleta;
    }

    public void setNome_maleta(String nome_maleta) {
        this.nome_maleta = nome_maleta;
    }
    public void casdastrarMaleta(Maletas maleta) throws ExceptionDAO{
    	try {
    		new MaletaDAO().cadastrarMaleta(maleta);
		} catch (ExceptionDAO e) {
			throw new ExceptionDAO("erro na model " + e);
		}
    	
    }

    @Override
    public String toString() {
    	
    	return this.getNome_maleta();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Maletas)) {
            return false;
        }

        Maletas other = (Maletas) obj;
        return Objects.equals(this.getNome_maleta(), other.getNome_maleta());
    }

	
        
}
