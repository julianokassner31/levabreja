package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Cliente;

public interface ClienteRepository {
	
	public List<Cliente> listar();
	
	public Cliente procurarCliente(Cliente cliente);
	
	public Cliente verificarSeLoginExiste(String login);
	
	public Cliente verificarSeCpfExiste(String cpf);
	
	public boolean salvar(Cliente novoCliente);
	
	public boolean atualizar(Cliente clienteSelecionado);
	
	public boolean apagar(int idCliente);

	

}
