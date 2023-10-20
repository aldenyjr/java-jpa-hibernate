package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");

//        Produto celular = new Produto("Xiome Redmi",
//                "Muito Legal", new BigDecimal("800"),celulares );

        EntityManager manager = JPAUtil.getEntityManager();
//        ProdutoDao produtoDao = new ProdutoDao(manager);
        CategoriaDao categoriaDao = new CategoriaDao(manager);

        manager.getTransaction().begin();

        manager.persist(celulares);
        celulares.setNome("XPTO");

//        categoriaDao.cadastrar(celulares);
//        produtoDao.cadastrar(celular);

        manager.flush();
        manager.clear();

        celulares = manager.merge(celulares);
//        celulares = categoriaDao.atualizar(celulares);
        celulares.setNome("1234");
        manager.flush();
        manager.remove(celulares);
//        categoriaDao.remover(celulares);
        manager.flush();
    }
}
