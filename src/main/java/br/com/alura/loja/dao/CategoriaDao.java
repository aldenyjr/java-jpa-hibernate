package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

    public Categoria atualizar(Categoria categoria){
         return this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        this.em.remove(em.merge(categoria));
    }
}
