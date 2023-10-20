package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;

import javax.persistence.EntityManager;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
    public void atualizar(Produto produto) { this.em.merge(produto);};

    public void remover(Produto produto){
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);

    }

    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String toNome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
//        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1;
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", toNome)
//                .setParameter(1, toNome)
                .getResultList();
    }

    public List<Produto> buscaPorNomeDaCategoria(String nomeCategoria){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1, nomeCategoria)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoPorNome(String nomeDoProduto){
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter(1, nomeDoProduto)
                .getSingleResult();
    }

}
