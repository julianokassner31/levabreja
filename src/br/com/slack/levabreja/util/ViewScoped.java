package br.com.slack.levabreja.util;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ViewScoped implements Scope {

	@SuppressWarnings("rawtypes")
	@Override
	public Object get(String arg0, ObjectFactory arg1) {
		Map<String,Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		 
        if(viewMap.containsKey(arg0)) {
            return viewMap.get(arg0);
        } else {
            Object object = arg1.getObject();
            viewMap.put(arg0, object);
 
            return object;
        }
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String arg0, Runnable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object remove(String arg0) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(arg0);
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
