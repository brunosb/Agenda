package agenda.Entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public  class Endereco implements Serializable {


    @Basic
    private String Pais;


    @Basic
    private String Estado;


    @Basic
    private String Cidade;

    public Endereco(){

    }


   public String getPais() {
        return this.Pais;
    }


  public void setPais (String Pais) {
        this.Pais = Pais;
    }



   public String getEstado() {
        return this.Estado;
    }


  public void setEstado (String Estado) {
        this.Estado = Estado;
    }



   public String getCidade() {
        return this.Cidade;
    }


  public void setCidade (String Cidade) {
        this.Cidade = Cidade;
    }

}

