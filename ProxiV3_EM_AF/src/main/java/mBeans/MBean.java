package mBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;

import metier.Conseiller;
import service.IConseillerService;
import service.ILoginService;
import service.Services;


@Named
@SessionScoped
public class MBean implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Inject
	private ILoginService iLogin=new Services();
	//@Inject
	private IConseillerService iService = new Services();
	
	private Conseiller conseiller= new Conseiller();

	public IConseillerService getiService() {
		return iService;
	}

	public void setiService(IConseillerService iService) {
		this.iService = iService;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public String verificationLogin() {
		Conseiller c = iLogin.verificationLogin(conseiller.getLogin(), conseiller.getPwd());
		
		if (c.getLogin().equalsIgnoreCase(conseiller.getLogin()) && c.getPwd().equals(conseiller.getPwd())) {
			return "listeClients";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("login", new FacesMessage("Invalid UserName and Password"));
			return "login";
		}
	}


}
