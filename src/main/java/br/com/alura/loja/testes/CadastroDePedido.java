package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(manager);
        Produto produto = produtoDao.buscarPorId(1L);

        ClienteDao clienteDao = new ClienteDao(manager);
        Cliente cliente = clienteDao.buscarPorId(1L);

        manager.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(manager);
        pedidoDao.cadastrar(pedido);



        manager.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total: " + totalVendido);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(vendas -> System.out.println(vendas));

    }

    private static void popularBancoDeDados() {
        EntityManager manager = JPAUtil.getEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(manager);
        ProdutoDao produtoDao = new ProdutoDao(manager);
        ClienteDao clienteDao = new ClienteDao(manager);
        manager.getTransaction().begin();

        Categoria categoria = new Categoria("FastFood");
        Produto bigbugger = new Produto("Big Burgger", "Hamburguer", new BigDecimal(19.90), categoria);
        Cliente cliente = new Cliente("Rodrigo", "123456");

        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(bigbugger);
        clienteDao.cadastrar(cliente);
        manager.getTransaction().commit();
        manager.clear();
    }
}
