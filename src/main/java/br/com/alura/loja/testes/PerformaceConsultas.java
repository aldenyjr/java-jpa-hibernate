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

public class PerformaceConsultas {
    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager manager = JPAUtil.getEntityManager();
        Pedido pedido = manager.find(Pedido.class, 1L);
        System.out.println(pedido.getItens().size());


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

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, bigbugger));

        PedidoDao pedidoDao = new PedidoDao(manager);
        pedidoDao.cadastrar(pedido);

        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(bigbugger);
        clienteDao.cadastrar(cliente);
        manager.getTransaction().commit();
        manager.clear();
    }
}
