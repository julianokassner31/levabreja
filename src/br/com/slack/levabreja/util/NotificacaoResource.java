package br.com.slack.levabreja.util;


import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/notificacao")
public class NotificacaoResource {

	@OnMessage(encoders = {JSONEncoder.class})
	public FacesMessage onMessage(FacesMessage message) {
		return message;
	}

}


