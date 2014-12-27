package agenda.Entity;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public  class Pessoa implements Serializable {


    @OneToOne 
    private Grupo Grupo;


    @ElementCollection
    private Collection<String> Telefone;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @Basic
    private String Nome;


    @Basic
    private String Sexo;


    @Embedded
    private Endereco Endereco;

    public Pessoa(){

    }

    public Grupo getGrupo() {
        return Grupo;
    }

    public void setGrupo(Grupo Grupo) {
        this.Grupo = Grupo;
    }


   public Collection<String> getTelefone() {
        return this.Telefone;
    }


  public void setTelefone (Collection<String> Telefone) {
        this.Telefone = Telefone;
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



   public String getSexo() {
        return this.Sexo;
    }


  public void setSexo (String Sexo) {
        this.Sexo = Sexo;
    }



   public Endereco getEndereco() {
        return this.Endereco;
    }


  public void setEndereco (Endereco Endereco) {
        this.Endereco = Endereco;
    }

}

