/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.Controller;

import agenda.Dao.DaoPessoa;
import agenda.Entity.Endereco;
import agenda.Entity.Grupo;
import agenda.Entity.Pessoa;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author BRUNO
 */
public class AgendaController implements Initializable {

    @FXML
    private TextArea txtTelefones;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPais;

    @FXML
    private TextField txtBusca;

    @FXML
    private Button btnUpgrade;

    @FXML
    private ComboBox<String> cbSexo;

    @FXML
    private Button btnAdd;

    @FXML
    private ProgressIndicator piCarregar;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField txtCidade;

    @FXML
    private TableView<TabelaModelo> tbMostrarContatos;
    @FXML
    private TableColumn colunaGrupo;
    @FXML
    private TableColumn colunaNome;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtGrupo;

    @FXML
    private ComboBox<String> cbGrupos;

    @FXML
    private Button btnAddGrupo;

    private Pessoa pessoa;
    private Grupo grupo;
    private Endereco endereco;
    private DaoPessoa daoPessoa = new DaoPessoa();
    private List<Pessoa> pessoaProcurada = new ArrayList<>();
    private Pessoa pessoaDaTabela;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar componentes
        piCarregar.setVisible(true);
        cbSexo.getItems().addAll("Masculino", "Feminino");
        cbSexo.setVisibleRowCount(2);
        
        for(int i=0;i<daoPessoa.consultaGrupo().size();i++){
            cbGrupos.getItems().add(daoPessoa.consultaGrupo().get(i).toString());
        }
        
        piCarregar.setVisible(false);
        
        // Adicionar Grupo ao Banco
        btnAddGrupo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                piCarregar.setVisible(true);
                grupo = new Grupo();
                grupo.setNome(txtGrupo.getText());
                daoPessoa.addGrupo(grupo);
                txtGrupo.setText("");
                piCarregar.setVisible(false);
            }
        });
        
        // Adicionar Contato ao Banco
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                piCarregar.setVisible(true);
                pessoa = new Pessoa();
                grupo = new Grupo();
                endereco = new Endereco();
                pessoa.setNome(txtNome.getText());
                pessoa.setSexo(cbSexo.getValue());
               
                for(int i=0;i<daoPessoa.consultaGrupo().size();i++){
                    Grupo g = (Grupo) daoPessoa.consultaGrupo().get(i);
                    if(g.getNome().equals(cbGrupos.getValue())){
                        grupo = g;
                    }
                }
                pessoa.setGrupo(grupo);
               
                endereco.setPais(txtPais.getText());
                endereco.setEstado(txtEstado.getText());
                endereco.setCidade(txtCidade.getText());
                pessoa.setEndereco(endereco);
                
                String fone = txtTelefones.getText();
                String[] fones = fone.split(";");
                List<String> tel = new ArrayList<>();
                for(int i=0;i<fones.length;i++){
                    tel.add(fones[i]);
                }
                pessoa.setTelefone(tel);
                
                daoPessoa.addPessoa(pessoa);
                
                txtNome.setText("");
                txtCidade.setText("");
                txtEstado.setText("");
                txtPais.setText("");
                txtTelefones.setText("");
                cbSexo.setValue("");
                cbGrupos.setValue("");
                piCarregar.setVisible(false);
            }
        });
        
        // Definindo parametros da tabela
        ObservableList<TabelaModelo> procurado = FXCollections.observableArrayList();
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));// "nome" é igual a variável declarada em TabelaModel
        colunaGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        tbMostrarContatos.setItems(procurado);
        
        // Campo de busca preenchendo a tabela
        txtBusca.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                piCarregar.setVisible(true);
                tbMostrarContatos.getItems().clear();
                pessoaProcurada = daoPessoa.consultaPessoa();
                
                for(int i=0;i<pessoaProcurada.size();i++){
                    if(pessoaProcurada.get(i).getNome().contains(txtBusca.getText())){
                        String name = pessoaProcurada.get(i).getNome();
                        String group = pessoaProcurada.get(i).getGrupo().getNome();
                        procurado.add(new TabelaModelo(name, group));
                    }
                }
                piCarregar.setVisible(false);
            }
        });
        
        // Evento de interação com a tabela
        tbMostrarContatos.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==1){
                    piCarregar.setVisible(true);
                    String pessoaProcurada = tbMostrarContatos.getSelectionModel().getSelectedItem().getNomeNaTabela();
                    List<Pessoa> pessoas = daoPessoa.consultaPessoa();
                    String numeros="";
                    for(int i=0;i<pessoas.size();i++){
                        Pessoa p = pessoas.get(i);
                        if(p.getNome().equals(pessoaProcurada)){
                            txtNome.setText(p.getNome());
                            txtCidade.setText(p.getEndereco().getCidade());
                            txtEstado.setText(p.getEndereco().getEstado());
                            txtPais.setText(p.getEndereco().getPais());
                            
                            List<String> telefones = daoPessoa.consultaTelefones(p);
                            for (int j=0; j<telefones.size();j++){
                                 numeros+= telefones.get(j)+";";
                            }
                            txtTelefones.setText(numeros);
                            cbSexo.setValue(p.getSexo());
                            cbGrupos.setValue(p.getGrupo().toString());
                            pessoaDaTabela = p;
                            piCarregar.setVisible(false);
                        }
                    }
                }
            }
        });
        
        // Atualizar Contato do Banco
        btnUpgrade.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                piCarregar.setVisible(true);
                grupo = new Grupo();
                endereco = new Endereco();
                
                pessoaDaTabela.setNome(txtNome.getText());
                pessoaDaTabela.setSexo(cbSexo.getValue());
               
                for(int i=0;i<daoPessoa.consultaGrupo().size();i++){
                    Grupo g = (Grupo) daoPessoa.consultaGrupo().get(i);
                    if(g.getNome().equals(cbGrupos.getValue())){
                        grupo = g;
                    }
                }
                pessoaDaTabela.setGrupo(grupo);
               
                endereco.setPais(txtPais.getText());
                endereco.setEstado(txtEstado.getText());
                endereco.setCidade(txtCidade.getText());
                pessoaDaTabela.setEndereco(endereco);
                
                String fone = txtTelefones.getText();
                String[] fones = fone.split(";");
                List<String> tel = new ArrayList<>();
                for(int i=0;i<fones.length;i++){
                    tel.add(fones[i]);
                }
                pessoaDaTabela.setTelefone(tel);
                
                daoPessoa.atualizarPessoa(pessoaDaTabela);
                
                txtNome.setText("");
                txtCidade.setText("");
                txtEstado.setText("");
                txtPais.setText("");
                txtTelefones.setText("");
                cbSexo.setValue("");
                cbGrupos.setValue("");
                piCarregar.setVisible(false);
            }
        });
        
        // Remover Contato do Banco
        btnRemove.setOnAction(event -> {piCarregar.setVisible(true);
                                        daoPessoa.removerPessoa(pessoaDaTabela);
                                        txtNome.setText("");
                                        txtCidade.setText("");
                                        txtEstado.setText("");
                                        txtPais.setText("");
                                        txtTelefones.setText("");
                                        cbSexo.setValue("");
                                        cbGrupos.setValue("");
                                        piCarregar.setVisible(false);});
    }

}
