package mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
public class MBeanListeClient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IConseillerService iConseiller = new Services();
	private Collection<Client> clients = new ArrayList<Client>();
	private Client client = new Client();

	public IConseillerService getiConseiller() {
		return iConseiller;
	}

	public void setiConseiller(IConseillerService iConseiller) {
		this.iConseiller = iConseiller;
	}

	public Collection<Client> getClients() {
		return clients;
	}

	public void setClients(Collection<Client> clients) {
		this.clients = clients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String afficherClient() {
		return "afficherClient";

	}

public Collection<Client> listClients(Conseiller conseiller) {
		
		return iConseiller.listerClients(conseiller);
	}

}
