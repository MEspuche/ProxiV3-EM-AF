package mBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import metier.Conseiller;
import service.IConseillerService;
import service.ILoginService;
import service.Services;

@ManagedBean
@SessionScoped
public class MBean implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	ILoginService iLogin = new Services();
	IConseillerService iService = new Services();
	private String login;
	private String pwd;

	public IConseillerService getiService() {
		return iService;
	}

	public void setiService(IConseillerService iService) {
		this.iService = iService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String verificationLogin() {
		Conseiller c = new Conseiller();
		c=(iLogin.verificationLogin(getLogin(), getPwd()));
		
		if (c.getLogin().equalsIgnoreCase(getLogin()) && c.getPwd().equals(getPwd())) {
			return "listeClients";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("login", new FacesMessage("Invalid UserName and Password"));
			return "login";
		}
	}


}
