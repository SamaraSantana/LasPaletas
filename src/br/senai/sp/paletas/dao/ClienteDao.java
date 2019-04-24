package br.senai.sp.paletas.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.senai.sp.paletas.modelo.Cliente;

public class ClienteDao extends AbstractDao {

	public void incluir(Cliente cliente) {
		manager.persist(cliente);
		commit();
	}

	public void excluir(Cliente cliente) {
		manager.remove(cliente);
		commit();
	}

	public void alterarCliente(Cliente cliente) {
		manager.merge(cliente);
		commit();
	}

	public Cliente buscar(int id) {
		return manager.find(Cliente.class, id);
	}

	public List<Cliente> buscar(String CPF) {
		TypedQuery<Cliente> query = manager.createQuery(
				"select c from Cliente c where c.cpf like :cpf", Cliente.class);
		query.setParameter("cpf", CPF +"%");
		List<Cliente> lista = query.getResultList();

			return lista;
	

	}

	public List<Cliente> listar() {
		TypedQuery<Cliente> query = manager.createQuery(
				"select c from Cliente c", Cliente.class);
		return query.getResultList();

	}

}
