package br.senai.sp.paletas.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import br.senai.sp.paletas.modelo.Funcionario;
import br.senai.sp.paletas.modelo.Genero;

public class FuncionarioDao extends AbstractDao {

	public void incluir(Funcionario funcionario) {
		manager.persist(funcionario);
		commit();
	}

	public void excluir(Funcionario funcionario) {
		manager.remove(funcionario);
		commit();
	}

	public void alterar(Funcionario funcionario) {
		manager.merge(funcionario);
		commit();
	}

	public List<Funcionario> buscar(String CPF) {
		TypedQuery<Funcionario> query = manager.createQuery(
				"select f from Funcionario f where f.cpf like :cpf",
				Funcionario.class);
		query.setParameter("cpf", CPF + "%");
		List<Funcionario> lista = query.getResultList();
		return lista;
	}

	public List<Funcionario> listar() {		
		TypedQuery<Funcionario> query = manager.createQuery(
				"select f from Funcionario f", Funcionario.class);
		return query.getResultList();
	}

	public Funcionario logar(String usuario, String senha){
		TypedQuery<Funcionario> query = manager.createQuery(
				"select f from Funcionario f where f.usuario = :usuario and f.senha = :senha",
				Funcionario.class);
		query.setParameter("usuario",usuario);
		query.setParameter("senha",senha);
		List<Funcionario> lista = query.getResultList();
		if (lista.size() == 0) {
			return null;
		}else {
			return lista.get(0);
		}
		
	}

	
}
