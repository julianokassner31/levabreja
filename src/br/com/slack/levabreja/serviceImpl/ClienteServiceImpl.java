package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.repository.ClienteRepository;
import br.com.slack.levabreja.service.ClienteService;

@Service
public class ClienteServiceImpl implements Serializable, ClienteService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listar() {
		return clienteRepository.listar();
	}

	@Override
	public boolean salvar(Cliente novoCliente) {
		return clienteRepository.salvar(novoCliente);
	}

	@Override
	public boolean atualizar(Cliente clienteSelecionado) {
		return clienteRepository.atualizar(clienteSelecionado);
	}

	@Override
	public boolean apagar(int idCliente) {
		return clienteRepository.apagar(idCliente);
	}

	@Override
	public Cliente procurarCliente(Cliente cliente) {
		return clienteRepository.procurarCliente(cliente);
	}

	@Override
	public Cliente verificarSeLoginExiste(String login) {
		return clienteRepository.verificarSeLoginExiste(login);
	}

	@Override
	public Cliente verificarSeCpfExiste(String cpf) {
		return clienteRepository.verificarSeCpfExiste(cpf);
	}

}
