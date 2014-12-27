/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.Dao;

import agenda.Entity.Grupo;
import agenda.Entity.Pessoa;
import agenda.Util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author BRUNO
 */
public class DaoPessoa {

    private Session sessao;
    private Transaction trans;

    public void addPessoa(Pessoa p) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(p.getNome());
            pessoa.setSexo(p.getSexo());
            pessoa.setGrupo(p.getGrupo());
            pessoa.setEndereco(p.getEndereco());
            pessoa.setTelefone(p.getTelefone());

            sessao.save(pessoa);
            trans.commit();

        } catch (Exception e) {
           
        } finally {
            sessao.close();
        }

    }

    public void atualizarPessoa(Pessoa p) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.update(p);
            trans.commit();

        } catch (Exception e) {
          
        } finally {
            sessao.close();
        }
    }

    public void removerPessoa(Pessoa p) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            sessao.delete(p);
            trans.commit();

        } catch (Exception e) {
            
        } finally {
            sessao.close();
        }
    }

    public void addGrupo(Grupo g) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();

            Grupo grupo = new Grupo();
            grupo.setNome(g.getNome());

            sessao.save(grupo);
            trans.commit();

        } catch (Exception e) {
           
        } finally {
            sessao.close();
        }
    }

    public List consultaGrupo() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        List<Grupo> g = new ArrayList<>();
        try {
            trans = sessao.beginTransaction();
            Criteria c = sessao.createCriteria(Grupo.class);
            g = c.list();
            
        } catch (Exception e) {
            
        } finally {
            sessao.close();
        }
        return g;
    }
    
    public List consultaPessoa(){
        sessao = HibernateUtil.getSessionFactory().openSession();
        List<Pessoa> pe = new ArrayList<>();
        try {
            trans = sessao.beginTransaction();
            Criteria c = sessao.createCriteria(Pessoa.class);
            pe = c.list();
        } catch (Exception e) {
            
        } finally {
            sessao.close();
        }
        return pe;
    }
    
    public List consultaTelefones(Pessoa p){
        sessao = HibernateUtil.getSessionFactory().openSession();
        List<String> telefones = new ArrayList<>();
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            trans = sessao.beginTransaction();
            Criteria c = sessao.createCriteria(Pessoa.class);
            pessoas = c.list();
            for(int i=0;i<pessoas.size();i++){
                Pessoa daLista = pessoas.get(i);
                if(daLista.getNome().equals(p.getNome())){
                    telefones.addAll(daLista.getTelefone());
                }
            }
            
        } catch (Exception e) {
           
        } finally {
            sessao.close();
        }
        return telefones;
    }

}
