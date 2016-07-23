package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listar();
	
	public Cliente verificarSeLoginExiste(String login);
	
	public Cliente verificarSeCpfExiste(String cpf);
	
	public boolean salvar(Cliente novoCliente);
	
	public boolean atualizar(Cliente clienteSelecionado);
	
	public boolean apagar(int idCliente);
	
	public Cliente procurarCliente(Cliente cliente);

}
