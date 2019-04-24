package br.senai.sp.paletas.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.Produto;

public class ProdutoDao extends AbstractDao{

	public void incluir(Produto produto) {
		manager.persist(produto);
		commit();
	}

	public void excluir(Produto produto) {
		manager.remove(produto);
		commit();
	}

	public void alterar(Produto produto) {
		manager.merge(produto);
		commit();
	}


	public List<Produto> buscar(String sabor) {
		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto p where p.sabor like :sabor", Produto.class);
		query.setParameter("sabor","%"+ sabor+"%");
		return query.getResultList();
	}

	public List<Produto> listar() {
		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto p", Produto.class);
		return query.getResultList();
	}

}
