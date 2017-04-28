package mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import metier.Client;
import metier.Compte;
import metier.CompteCourant;
import metier.CompteEpargne;
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
	private Collection<CompteCourant> compteCourants = new ArrayList<CompteCourant>();
	private Collection<CompteEpargne> compteEpargnes = new ArrayList<CompteEpargne>();
	private Compte compte;
	
	
	
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

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
	public Collection<CompteCourant> afficherCompteCourantClient(Conseiller co, Client cl){
		for (Compte c : iConseiller.afficherClient(co,cl.getId()).getComptes()){
			if (c instanceof CompteCourant){
				compteCourants.add((CompteCourant)c);
			}
		}
		
		return compteCourants;
	}
	
	public Collection<CompteEpargne> afficherCompteEpargneClient(Conseiller co, Client cl){
		for (Compte c : iConseiller.afficherClient(co,cl.getId()).getComptes()){
			if (c instanceof CompteEpargne){
				compteEpargnes.add((CompteEpargne)c);
			}
		}
		
		return compteEpargnes;
	}
	
	
	public String virement(Compte c){
		compte= c;
		return "effectuerVirement";
	}
	
}
