package br.senai.sp.paletas.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.senai.sp.paletas.modelo.ItemDoPedido;
import br.senai.sp.paletas.modelo.Pedido;

public class PedidoDao extends AbstractDao {
	public void incluir(Pedido pedido) {
		for (ItemDoPedido item : pedido.getItens()) {
			manager.persist(item);
		}
		manager.persist(pedido);
		commit();
	}

	public void excluir(Pedido pedido) {
		manager.remove(pedido);
		commit();
	}

	public Pedido buscar(int id) {
		return manager.find(Pedido.class, id);
	}

	public List<Pedido> listar() {
		TypedQuery<Pedido> query = manager.createQuery(
				"select p from Pedido p", Pedido.class);
		return query.getResultList();
	}
}
