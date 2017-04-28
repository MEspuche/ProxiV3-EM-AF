package mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import metier.Compte;
import service.IConseillerService;
import service.Services;

@ManagedBean
@Named
@SessionScoped
public class MBeanEffectuerVirement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IConseillerService iService = new Services();
	private Collection<Compte> comptes = new ArrayList<Compte>();
	private Compte compteCred;

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	public Compte getCompteCred() {
		return compteCred;
	}

	public void setCompteCred(Compte compteCred) {
		this.compteCred = compteCred;
	}

	public IConseillerService getiService() {
		return iService;
	}

	public void setiService(IConseillerService iService) {
		this.iService = iService;
	}

	public  Collection<Compte> colCompte(Compte c){
		return comptes = iService.recupererAutresComptes(c);
		
	}
	
	public String virement(Compte c){
		compteCred=c;
		return "montantvir";
	}
	
	

}
