package mBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import metier.Conseiller;
import service.IConseillerService;
import service.Services;

@ManagedBean
@Named
@SessionScoped
public class MBeanModifierClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IConseillerService iService = new Services();
	
	
	
	public IConseillerService getiService() {
		return iService;
	}



	public void setiService(IConseillerService iService) {
		this.iService = iService;
	}



	public String modifierClient(Conseiller conseiller, int idClient, String nom, String prenom, String email, String adresse, String codePostal, String ville, String telephone)
	{
		iService.modifierClient(conseiller, idClient, nom, prenom, email, adresse, codePostal, ville, telephone);
		return "afficherClient";
	}

}
