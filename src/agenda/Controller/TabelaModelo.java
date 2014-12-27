/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BRUNO
 * 
 * Declarar o Model da Tabela
 */
public class TabelaModelo {
    private StringProperty nome;
    private StringProperty grupo;
    private String nomeNaTabela;
    
    public TabelaModelo(String n, String g){
        this.nome = new SimpleStringProperty(n);
        this.grupo = new SimpleStringProperty(g);
        this.nomeNaTabela = n;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty grupoProperty() {
        return grupo;
    }

    public String getNomeNaTabela() {
        return nomeNaTabela;
    }
    
}
