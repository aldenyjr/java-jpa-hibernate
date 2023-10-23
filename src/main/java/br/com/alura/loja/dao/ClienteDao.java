package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        this.em.persist(cliente);
    }
    public void atualizar(Cliente cliente) { this.em.merge(cliente);};

    public void remover(Cliente cliente){
        cliente = em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscarPorId(Long id){
        return em.find(Cliente.class, id);

    }

//    public List<Produto> buscarTodos(){
//        String jpql = "SELECT p FROM Produto p";
//        return em.createQuery(jpql, Produto.class).getResultList();
//    }
//
//    public List<Produto> buscarPorNome(String toNome){
//        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
////        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1;
//        return em.createQuery(jpql, Produto.class)
//                .setParameter("nome", toNome)
////                .setParameter(1, toNome)
//                .getResultList();
//    }
//
//    public List<Produto> buscaPorNomeDaCategoria(String nomeCategoria){
//        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
//        return em.createQuery(jpql, Produto.class)
//                .setParameter(1, nomeCategoria)
//                .getResultList();
//    }
//
//    public BigDecimal buscarPrecoDoProdutoPorNome(String nomeDoProduto){
//        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
//        return em.createQuery(jpql, BigDecimal.class)
//                .setParameter(1, nomeDoProduto)
//                .getSingleResult();
//    }

}
