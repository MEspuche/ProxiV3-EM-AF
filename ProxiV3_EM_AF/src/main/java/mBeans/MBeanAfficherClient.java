package mBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import metier.Client;
import metier.Conseiller;
import service.IConseillerService;
import service.Services;

@ManagedBean
@Named
@SessionScoped
public class MBeanAfficherClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IConseillerService iConseiller = new Services();
	private Client client = new Client();

	
	public IConseillerService getiConseiller() {
		return iConseiller;
	}

	public void setiConseiller(IConseillerService iConseiller) {
		this.iConseiller = iConseiller;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	public Client afficherClient(Conseiller co, Client cl){
		client= iConseiller.afficherClient(co,cl.getId());
		return client;
	}
	public String virement(){
		return "effectuerVirement";
	}
	
}
