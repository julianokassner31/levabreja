package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Empresa;
import br.com.slack.levabreja.repository.EmpresaRepository;
import br.com.slack.levabreja.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements Serializable, EmpresaService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public List<Empresa> listar() {
		return empresaRepository.listar();
	}
	
	public List<Empresa> listarEmpresasAtivadas(){
		return empresaRepository.listarEmpresasAtivadas();
	}

	@Override
	public Empresa buscar(int idEmpresa) {
		return empresaRepository.buscar(idEmpresa);
	}

	@Override
	public boolean salvar(Empresa novaEmpresa) {
		return empresaRepository.salvar(novaEmpresa);
		
	}

	@Override
	public boolean atualizar(Empresa empresaSelecionada) {
		return empresaRepository.atualizar(empresaSelecionada);
		
	}

	@Override
	public boolean apagar(int idEmpresa) {
		return empresaRepository.apagar(idEmpresa);
		
	}

}
