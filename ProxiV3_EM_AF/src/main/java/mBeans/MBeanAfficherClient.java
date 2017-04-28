package mBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import metier.Client;

@ManagedBean
@Named
@SessionScoped
public class MBeanAfficherClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client client = new Client();

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Collection<Compte> listComptes(){
		return 
	}
	
	public String virementComptetf 
	
}
