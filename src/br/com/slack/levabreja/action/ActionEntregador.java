package br.com.slack.levabreja.action;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Entregador;
import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.EntregadorService;
import br.com.slack.levabreja.service.PedidoService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionEntregador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntregadorService entregadorService; 
	
	@Autowired
	private PedidoService pedidoService;
	
	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	private List <Entregador> listaDeEntregadores;
	private List<Pedido> listaDePedidosDoEntregador;
	private Entregador novoEntregador;
	private Entregador entregadorSelecionado;
	
	@PostConstruct
	public void inicio(){
		
		//atirbui o objeto da session para usuarioEmpresa
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		
		//faz a verificação de um usuario
		if (usuarioEmpresaLogado != null){
		
			listaDeEntregadores = listarEntregadores();
			
			listaDePedidosDoEntregador = new ArrayList<Pedido>();
			
			entregadorSelecionado = new Entregador();
			
			novoEntregador = new Entregador();
		
		}else{
		//se não encontar um usuario valido redireciona para a pagina de login
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//metodo para listar todos os entregadores da empresa
	public List<Entregador> listarEntregadores(){
		return entregadorService.listar(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	//salva um obejto entregador no banco
	public void salvar(){

		if(!novoEntregador.getNome().trim().equals("") || !novoEntregador.getTelefone().trim().equals("")){ 		

			//seta com o id da empresa o nov usuario criado
			novoEntregador.setEmpresa(usuarioEmpresaLogado.getEmpresa());
			
			//deixa o novo entregador ativo
			novoEntregador.setAtivado(true);
			
			//salvo o objeto no banco
			entregadorService.salvar(novoEntregador);
			
			//add o novo obejto na lista ja instanciada
			listaDeEntregadores.add(novoEntregador);
			
			// cria um nova instancia para pode ser usado em seguida
			novoEntregador = new Entregador();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!", null));

		}else{

			//caso acontercer algum erro, apaga o objeto instanciado e cria outro
			novoEntregador = new Entregador();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
		}
	}

	public void atualizar(){
		
		if(listaDePedidosDoEntregador.isEmpty() && !entregadorSelecionado.getNome().equals("") &&
				!entregadorSelecionado.getTelefone().trim().equals("")){
			
			//atualiza o obejto no banco
			entregadorService.atualizar(entregadorSelecionado);
				
			entregadorSelecionado = new Entregador();
				
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
		
		}else{
				
			//busca a lista novamente para que a lista nao fique modificada,
			//sem que seja salvo um objeto no banco
			listaDeEntregadores = listarEntregadores();
				
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
		}
	}
	
	public void apagar(){
			
		if(listaDePedidosDoEntregador.isEmpty()){
		
			if(entregadorService.apagar(entregadorSelecionado.getIdEntregador())){
				
				listaDeEntregadores.remove(entregadorSelecionado);
				entregadorSelecionado = new Entregador();
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
							(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
			}
			
		}else{
			
			//busca a lista novamente para que a lista nao fique modificada,
			//sem que seja salvo um objeto no banco
			listaDeEntregadores = listarEntregadores();
			System.out.println(entregadorSelecionado.getNome());
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro!",null));
		}
	}
	//verifica se um entregador tem um pedido com status em espera
	//caso positivo o mesmo nao pode ser apagado ou modificado
	public void listarPedidosDoEntregador(){
		
		//tras o objeto para lista
		listaDePedidosDoEntregador = pedidoService.listarPedidosSaiuEntregador(entregadorSelecionado.getNome(), "saiu para entrega");
	}

	//gets e seters
	public List<Entregador> getListaDeEntregadores() {
		return listaDeEntregadores;
	}

	public void setListaDeEntregadores(List<Entregador> listaDeEntregadores) {
		this.listaDeEntregadores = listaDeEntregadores;
	}

	public Entregador getNovoEntregador() {
		return novoEntregador;
	}

	public void setNovoEntregador(Entregador novoEntregador) {
		this.novoEntregador = novoEntregador;
	}

	public Entregador getEntregadorSelecionado() {
		return entregadorSelecionado;
	}

	public void setEntregadorSelecionado(Entregador entregadorSelecionado) {
		this.entregadorSelecionado = entregadorSelecionado;
	}

	public LoginUsuarioEmpresa getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioEmpresa login) {
		this.login = login;
	}

	public UsuarioEmpresa getUsuarioEmpresaLogado() {
		return usuarioEmpresaLogado;
	}

	public void setUsuarioEmpresaLogado(UsuarioEmpresa usuarioEmpresaLogado) {
		this.usuarioEmpresaLogado = usuarioEmpresaLogado;
	}
}
