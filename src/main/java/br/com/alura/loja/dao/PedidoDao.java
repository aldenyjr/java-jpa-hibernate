package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }
    public void atualizar(Pedido pedido) { this.em.merge(pedido);};

    public void remover(Pedido pedido){
        pedido = em.merge(pedido);
        this.em.remove(pedido);
    }

    public Pedido buscarPorId(Long id){
        return em.find(Pedido.class, id);

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


    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

//    public List<Object[]> relatorioDeVendas(){
//        String jpql = "SELECT produto.nome,"
//                + "SUM(item.quantidade) as qtd,"
//                + "MAX(pedido.data) "
//                + "FROM Pedido pedido "
//                + "JOIN pedido.itens item "
//                + "JOIN item.produto produto "
//                + "GROUP BY produto.nome "
//                + "ORDER BY qtd DESC";
//        return em.createQuery(jpql, Object[].class).getResultList();
//    }

    public List<RelatorioDeVendasVo> relatorioDeVendas(){
        String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(" +
                "produto.nome,"
                + "SUM(item.quantidade) as qtd,"
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY qtd DESC";
        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

}
