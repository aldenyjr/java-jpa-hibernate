package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {
    public static void main(String[] args) {

        cadastrarProdutoComCategoria();

    }

    private static void cadastrarProdutoComCategoria() {
        EntityManager manager = JPAUtil.getEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(manager);
        ProdutoDao produtoDao = new ProdutoDao(manager);

        Categoria categoria = new Categoria("FastFood");
        Produto bigbugger = new Produto("Big Burgger", "Hamburguer", new BigDecimal(19.90), categoria);

        manager.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(bigbugger);
        manager.getTransaction().commit();
        manager.clear();
    }
}
