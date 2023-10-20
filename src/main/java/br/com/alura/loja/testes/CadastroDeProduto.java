package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(manager);

        Produto produto = produtoDao.buscarPorId(1L);
        System.out.println(produto.getPreco());

        List<Produto> todos = produtoDao.buscarTodos();
        for (Produto p: todos) {
            System.out.println(p);
        }

        List<Produto> todoPorNome = produtoDao.buscarPorNome("Xiome Redmi");
        for (Produto p: todoPorNome) {
            System.out.println(p);
        }

        List<Produto> todosPorCategoria = produtoDao.buscaPorNomeDaCategoria("TELEVISORES");
        for (Produto p: todosPorCategoria){
            System.out.println(p);
        }

        System.out.println(produtoDao.buscarPrecoDoProdutoPorNome("Xiome Redmi"));

    }

    private static void cadastrarProduto() {
        EntityManager manager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(manager);
        CategoriaDao categoriaDao = new CategoriaDao(manager);
        manager.getTransaction().begin();

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiome Redmi",
                "Muito Legal", new BigDecimal("800"),celulares);

        manager.persist(celulares);
//        celulares.setNome("XPTO");

//        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        manager.getTransaction().commit();
//        manager.flush();
        manager.clear();

//        celulares = manager.merge(celulares);
//        celulares = categoriaDao.atualizar(celulares);
//        celulares.setNome("1234");
//        manager.flush();
//        manager.remove(celulares);
//        categoriaDao.remover(celulares);
//        manager.flush();

        Categoria televidores = new Categoria("TELEVISORES");
        Produto smarttv = new Produto("LG G-500",
                "Muito Legal", new BigDecimal("1600"),televidores);

        manager.getTransaction().begin();
        manager.persist(televidores);
        produtoDao.cadastrar(smarttv);
        manager.getTransaction().commit();
        manager.clear();



    }
}
