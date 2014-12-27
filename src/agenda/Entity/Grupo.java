package agenda.Entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Grupo implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @Basic
    private String Nome;

    public Grupo(){
        
    }


   public Long getId() {
        return this.id;
    }


  public void setId (Long id) {
        this.id = id;
    }



   public String getNome() {
        return this.Nome;
    }


  public void setNome (String Nome) {
        this.Nome = Nome;
    }
  
  @Override
  public String toString(){
        return getNome();
  }
  
}

